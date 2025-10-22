package dev.furkankeskin.dto;

import lombok.Data;

@Data
public class GalleristDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private AddressDTO address;
}
