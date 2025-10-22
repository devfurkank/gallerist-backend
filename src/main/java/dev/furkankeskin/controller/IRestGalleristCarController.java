package dev.furkankeskin.controller;

import dev.furkankeskin.dto.GalleristCarDTO;
import dev.furkankeskin.dto.GalleristCarDTOIU;

public interface IRestGalleristCarController {

    public RootEntity<GalleristCarDTO> saveGalleristCar(GalleristCarDTOIU galleristCarDTOIU);
}
