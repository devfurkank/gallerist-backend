package dev.furkankeskin.service;

import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;

public interface ICarService {

    public CarDTO saveCar(CarDTOIU carDTOIU);
}
