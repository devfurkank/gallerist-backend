package dev.furkankeskin.dto;

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
