package dev.furkankeskin.service;

import dev.furkankeskin.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}
