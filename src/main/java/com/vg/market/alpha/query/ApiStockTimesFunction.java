package com.vg.market.alpha.query;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ApiStockTimesFunction
{
    Intraday("/intraday", "TIME_SERIES_INTRADAY"),
    IntradayExtended("/intradayExt", "TIME_SERIES_INTRADAY_EXTENDED"),
    Daily("/daily", "TIME_SERIES_DAILY"),
    DailyAdjusted("/dailyAdj", "TIME_SERIES_DAILY_ADJUSTED"),
    Weekly("/weekly", "TIME_SERIES_WEEKLY"),
    WeeklyAdjusted("/weeklyAdj", "TIME_SERIES_WEEKLY_ADJUSTED"),
    Monthly("/monthly", "TIME_SERIES_MONTHLY"),
    MonthlyAdjusted("/monthlyAdj", "TIME_SERIES_MONTHLY_ADJUSTED");

    private final static Map<String, ApiStockTimesFunction> alphaTimes;
    static {
        alphaTimes = Arrays.stream(ApiStockTimesFunction.values()).collect(Collectors.toMap(ApiStockTimesFunction::getUrl, Function.identity()));
    }

    String url;
    String value;

    ApiStockTimesFunction(String url, String value) {
        this.url = url;
        this.value = value;
    }

    public static Map<String, ApiStockTimesFunction> getMap()
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
