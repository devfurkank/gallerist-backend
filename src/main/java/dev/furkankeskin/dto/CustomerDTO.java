package dev.furkankeskin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO extends BaseDTO {

    private  String firstName;

    private String lastName;

    private String tckn;

    private Date birthDate;

    private AddressDTO address;

    private AccountDTO account;
}
