package dev.furkankeskin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaledCarDTOIU {

    @NotNull
    private Long customerId;

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;
}
