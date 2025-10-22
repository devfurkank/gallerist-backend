package dev.furkankeskin.dto;

import dev.furkankeskin.model.Account;
import dev.furkankeskin.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTOIU {

    @NotNull
    private  String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    private Date birthDate;

    @NotNull
    private Long addressId;

    @NotNull
    private Long accountId;
}
