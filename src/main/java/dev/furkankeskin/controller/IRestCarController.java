package dev.furkankeskin.controller;

import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;

import java.util.List;

public interface IRestCarController {

    public RootEntity<CarDTO> saveCar(CarDTOIU carDTOIU);

    public RootEntity<List<CarDTO>> getAllCars();

    public RootEntity<CarDTO> updateCar(Long id, CarDTOIU carDTOIU);

    public RootEntity<String> deleteCar(Long id);
}
