package com.vg.market.alpha.query;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ApiCryptoTimesFunction
{
    Intraday("/intraday", "CRYPTO_INTRADAY"),
    Daily("/daily", "DIGITAL_CURRENCY_DAILY"),
    Weekly("/weekly", "DIGITAL_CURRENCY_WEEKLY"),
    Monthly("/monthly", "DIGITAL_CURRENCY_MONTHLY");

    private final static Map<String, ApiCryptoTimesFunction> alphaTimes;
    static {
        alphaTimes = Arrays.stream(ApiCryptoTimesFunction.values()).collect(Collectors.toMap(ApiCryptoTimesFunction::getUrl, Function.identity()));
    }

    String url;
    String value;

    ApiCryptoTimesFunction(String url, String value) {
        this.url = url;
        this.value = value;
    }

    public static Map<String, ApiCryptoTimesFunction> getMap()
    {
        return alphaTimes;
    }

    public String getUrl()
    {
        return url;
    }

    public String getValue() {
        return value;
    }
}
