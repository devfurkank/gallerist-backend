package dev.furkankeskin.dto;

import lombok.Data;

@Data
public class AddressDTO extends BaseDTO {

    private String city;

    private String street;

    private String district;

    private String neighborhood;
}
