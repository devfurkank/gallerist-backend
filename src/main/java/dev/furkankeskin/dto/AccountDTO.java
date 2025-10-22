package dev.furkankeskin.dto;

import dev.furkankeskin.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO extends BaseDTO {

    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;
}
