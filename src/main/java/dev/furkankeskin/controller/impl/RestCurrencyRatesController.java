package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestCurrencyRatesController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.CurrencyRatesResponse;
import dev.furkankeskin.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesController extends RestBaseController implements IRestCurrencyRatesController {

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @GetMapping("/currency-rates")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
    }
}
