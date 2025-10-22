package dev.furkankeskin.controller;

import dev.furkankeskin.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRatesResponse>  getCurrencyRates(String startDate, String endDate);
}
