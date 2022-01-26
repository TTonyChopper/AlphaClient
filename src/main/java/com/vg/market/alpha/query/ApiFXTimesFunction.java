package com.vg.market.alpha.query;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ApiFXTimesFunction
{
    Intraday("/intraday", "FX_INTRADAY"),
    Daily("/daily", "FX_DAILY"),
    Weekly("/weekly", "FX_WEEKLY"),
    Monthly("/monthly", "FX_MONTHLY");

    private final static Map<String, ApiFXTimesFunction> alphaTimes;
    static {
        alphaTimes = Arrays.stream(ApiFXTimesFunction.values()).collect(Collectors.toMap(ApiFXTimesFunction::getUrl, Function.identity()));
    }

    String url;
    String value;

    ApiFXTimesFunction(String url, String value) {
        this.url = url;
        this.value = value;
    }

    public static Map<String, ApiFXTimesFunction> getMap()
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
