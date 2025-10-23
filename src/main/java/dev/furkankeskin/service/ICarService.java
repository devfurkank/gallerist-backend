package dev.furkankeskin.service;

import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;

import java.util.List;

public interface ICarService {

    public CarDTO saveCar(CarDTOIU carDTOIU);

    public List<CarDTO> getAllCars();

    public CarDTO updateCar(Long id, CarDTOIU carDTOIU);

    public String deleteCar(Long id);
}
