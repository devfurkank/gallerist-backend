package dev.furkankeskin.dto;

import lombok.Data;

@Data
public class GalleristCarDTO extends BaseDTO {

    private GalleristDTO  gallerist;

    private CarDTO car;
}
