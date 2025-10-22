package dev.furkankeskin.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddressDTOIU {

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    private String district;

    @NotEmpty
    private String neighborhood;
}
