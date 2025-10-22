package dev.furkankeskin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyRatesItems {

    @JsonProperty("Tarih")
    private String date;

    @JsonProperty("TP_DK_USD_A")
    private String usd;
}
