package com.vg.market.alpha.query;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ApiFundamentalFunction
{
    GlobalQuote("/quote", "GLOBAL_QUOTE"),
    CompanyOverview("/overview", "OVERVIEW"),
    IncomeStatement("/income", "INCOME_STATEMENT"),
    BalanceSheet("/balancesheet", "BALANCE_SHEET");

    private final static Map<String, ApiFundamentalFunction> alphaFundamentals;
    static {
        alphaFundamentals = Arrays.stream(ApiFundamentalFunction.values()).collect(Collectors.toMap(ApiFundamentalFunction::getUrl, Function.identity()));
    }

    String url;
    String value;

    ApiFundamentalFunction(String url, String value) {
        this.url = url;
        this.value = value;
    }

    public static Map<String, ApiFundamentalFunction> getMap()
    {
        return alphaFundamentals;
    }

    public String getUrl()
    {
        return url;
    }

    public String getValue() {
        return value;
    }
}
