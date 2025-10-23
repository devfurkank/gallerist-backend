package dev.furkankeskin.dto;

import lombok.Data;

@Data
public class SaledCarDTO extends BaseDTO{

    private CustomerDTO customer;

    private GalleristDTO gallerist;

    private CarDTO car;
}
