package com.vg.market;

import com.vg.market.alpha.query.*;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.*;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlphaVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger("SampleLogger");

    private AlphaQueryBuilder queryBuilder;

    private HttpServer httpServer;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new AlphaVerticle());
    }

    @Override
    public void start() {
        WebClient client = WebClient.create(vertx);
        final Router router = Router.router(vertx);

        ConfigRetriever retriever = ConfigRetriever.create(vertx);
        retriever.getConfig(ar -> {
            if (ar.failed()) {
                // Failed to retrieve the configuration
            } else {
                JsonObject config = ar.result();
                queryBuilder = new AlphaQueryBuilder(config.getString("apikey"));
            }
        });

        ApiCryptoTimesFunction.getMap().forEach((k, v)->{
            router.route("/raw/crypto" + k).handler(ctx -> {

                String from = ctx.request().getParam("from");
                from = (from == null) ? "BTC" : from;
                String to = ctx.request().getParam("to");
                to = (to == null) ? "EUR" : to;
                String interval = ctx.request().getParam("interval");
                interval = (interval == null) ? "5min" : interval;

                client
                        .get(443, AlphaQueryBuilder.ALPHA_BASE_URL, queryBuilder.getCryptoTimes(v, from, to, interval))
                        .ssl(true)
                        .send()
                        .onSuccess(response -> {
                            log.debug("Received response with status code" + response.statusCode());
                            ctx.response().putHeader("content-type", "text/json")
                                    .send(response.body());
                        })
                        .onFailure(err ->
                                log.debug("Something went wrong " + err.getMessage()));
            });
        });

        ApiFundamentalFunction.getMap().forEach((k, v)->{
            router.route("/raw" + k).handler(ctx -> {

                String symbol = ctx.request().getParam("symbol");
                symbol = (symbol == null) ? "TSLA" : symbol;

                client
                        .get(443, AlphaQueryBuilder.ALPHA_BASE_URL, queryBuilder.getFundamentals(v, symbol))
                        .ssl(true)
                        .send()
                        .onSuccess(response -> {
                            log.debug("Received response with status code" + response.statusCode());
                            ctx.response().putHeader("content-type", "text/json")
                                    .send(response.body());
                        })
                        .onFailure(err ->
                                System.out.println("Something went wrong " + err.getMessage()));
            });
        });

        ApiFXTimesFunction.getMap().forEach((k, v)->{
            router.route("/raw/fx" + k).handler(ctx -> {

                String from = ctx.request().getParam("from");
                from = (from == null) ? "USD" : from;
                String to = ctx.request().getParam("to");
                to = (to == null) ? "EUR" : to;
                String interval = ctx.request().getParam("interval");
                interval = (interval == null) ? "5min" : interval;

                client
                        .get(443, AlphaQueryBuilder.ALPHA_BASE_URL, queryBuilder.getFXTimes(v, from, to, interval))
                        .ssl(true)
                        .send()
                        .onSuccess(response -> {
                            log.debug("Received response with status code" + response.statusCode());
                            ctx.response().putHeader("content-type", "text/json")
                                    .send(response.body());
                        })
                        .onFailure(err ->
                                log.debug("Something went wrong " + err.getMessage()));
            });
        });

        ApiStockTimesFunction.getMap().forEach((k, v)->{
            router.route("/raw/stock" + k).handler(ctx -> {

                String symbol = ctx.request().getParam("symbol");
                symbol = (symbol == null) ? "TSLA" : symbol;
                String interval = ctx.request().getParam("interval");
                interval = (interval == null) ? "5min" : interval;

                client
                        .get(443, AlphaQueryBuilder.ALPHA_BASE_URL, queryBuilder.getStockTimes(v, symbol, interval))
                        .ssl(true)
                        .send()
                        .onSuccess(response -> {
                            log.debug("Received response with status code" + response.statusCode());
                            ctx.response().putHeader("content-type", "text/json")
                                    .send(response.body());
                        })
                        .onFailure(err ->
                                log.debug("Something went wrong " + err.getMessage()));
            });
        });

        router.route("/alive").handler(ctx -> {
            System.out.println("Hello");
            ctx.response().putHeader("content-type", "text/plain")
                    .send("Hello");
        });

        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router)
                .listen(9091)
                .onFailure(h->System.out.println("HTTP server failed on port 9091"));

    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
        httpServer.close();
    }
}
