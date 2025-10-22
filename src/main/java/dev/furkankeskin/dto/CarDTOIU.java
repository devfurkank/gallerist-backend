package dev.furkankeskin.dto;

import dev.furkankeskin.enums.CarStatusType;
import dev.furkankeskin.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDTOIU {

    @NotNull
    private String plaka;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Integer productionYear;

    @NotNull
    private BigDecimal price;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;
}
