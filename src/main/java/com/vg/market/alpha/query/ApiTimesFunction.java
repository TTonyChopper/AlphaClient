package com.vg.market.alpha.query;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ApiTimesFunction
{
    Intraday("/intraday", "TIME_SERIES_INTRADAY"),
    IntradayExtended("/intradayExt", "TIME_SERIES_INTRADAY_EXTENDED"),
    Daily("/daily", "TIME_SERIES_DAILY"),
    DailyAdjusted("/dailyAdj", "TIME_SERIES_DAILY_ADJUSTED"),
    Weekly("/weekly", "TIME_SERIES_WEEKLY"),
    WeeklyAdjusted("/weeklyAdj", "TIME_SERIES_WEEKLY_ADJUSTED"),
    Monthly("/monthly", "TIME_SERIES_MONTHLY"),
    MonthlyAdjusted("/monthlyAdj", "TIME_SERIES_MONTHLY_ADJUSTED");

    private final static Map<String, ApiTimesFunction> alphaTimes;
    static {
        alphaTimes = Arrays.stream(ApiTimesFunction.values()).collect(Collectors.toMap(ApiTimesFunction::getUrl, Function.identity()));
    }

    String url;
    String value;

    ApiTimesFunction(String url, String value) {
        this.url = url;
        this.value = value;
    }

    public static Map<String, ApiTimesFunction> getMap()
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
