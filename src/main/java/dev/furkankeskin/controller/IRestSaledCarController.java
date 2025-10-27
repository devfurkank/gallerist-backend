package dev.furkankeskin.controller;

import dev.furkankeskin.dto.SaledCarDTO;
import dev.furkankeskin.dto.SaledCarDTOIU;

import java.util.List;

public interface IRestSaledCarController {

    public RootEntity<SaledCarDTO> buyCar(SaledCarDTOIU saledCarDTOIU);

    public RootEntity<List<SaledCarDTO>> getAllSaledCars();

    public RootEntity<SaledCarDTO> updateSaledCar(Long id, SaledCarDTOIU saledCarDTOIU);

    public RootEntity<String> deleteSaledCar(Long id);
}
