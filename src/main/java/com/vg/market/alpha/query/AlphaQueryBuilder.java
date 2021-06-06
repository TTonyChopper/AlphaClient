package com.vg.market.alpha.query;

public class AlphaQueryBuilder
{
    public final static String ALPHA_BASE_URL = "www.alphavantage.co";

    private final String key;

    public AlphaQueryBuilder(String key) {
        this.key = key;
    }

    public String getFundamentals(ApiFundamentalFunction function, String symbol) {
        return "/query?function=" + function.getValue()
                +"&symbol=" + symbol
                +"&apikey=" + key;
    }

    public String getTimes(ApiTimesFunction function, String symbol, String interval) {
        return "/query?function=" + function.getValue()
                +"&symbol=" + symbol
                +"&apikey=" + key
                +"&interval=" + interval;
    }
}
