package dev.furkankeskin.dto;

import dev.furkankeskin.enums.CarStatusType;
import dev.furkankeskin.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDTO extends BaseDTO {

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
