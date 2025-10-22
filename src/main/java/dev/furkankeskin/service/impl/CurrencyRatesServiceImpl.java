package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.CurrencyRatesResponse;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";

        String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "bMi0ErF5ik");

        HttpEntity<?>  httpEntity = new HttpEntity<>(httpHeaders);
        
        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
            });
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.CURRENCY_RATES_IS_OCCURED));
        }
        return null;
    }
}
