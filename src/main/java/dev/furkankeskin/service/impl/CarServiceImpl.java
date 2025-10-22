package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;
import dev.furkankeskin.model.Car;
import dev.furkankeskin.repository.CarRepository;
import dev.furkankeskin.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCar(CarDTOIU carDTOIU) {
        Car car = new Car();
        car.setCreateTime(new Date());

        BeanUtils.copyProperties(carDTOIU, car);
        return car;
    }

    @Override
    public CarDTO saveCar(CarDTOIU carDTOIU) {
        CarDTO carDTO = new CarDTO();
        Car savedCar = carRepository.save(createCar(carDTOIU));

        BeanUtils.copyProperties(savedCar, carDTO);
        return carDTO;
    }
}
