package dev.furkankeskin.dto;

import dev.furkankeskin.enums.CurrencyType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTOIU{

    @NotEmpty
    private String accountNo;

    @NotEmpty
    private String iban;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private CurrencyType currencyType;
}
