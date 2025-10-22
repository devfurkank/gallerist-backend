package dev.furkankeskin.controller;

import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;

public interface IRestCarController {

    public RootEntity<CarDTO> saveCar(CarDTOIU carDTOIU);
}
