package dev.furkankeskin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GalleristCarDTOIU {

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;
}
