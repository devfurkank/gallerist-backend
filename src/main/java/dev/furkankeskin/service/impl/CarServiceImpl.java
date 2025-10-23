package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.model.Car;
import dev.furkankeskin.repository.CarRepository;
import dev.furkankeskin.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> carDTOs = new ArrayList<>();

        for (Car car : cars) {
            CarDTO carDTO = new CarDTO();
            BeanUtils.copyProperties(car, carDTO);
            carDTOs.add(carDTO);
        }
        return carDTOs;
    }

    @Override
    public CarDTO updateCar(Long id, CarDTOIU carDTOIU) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage("", MessageType.NO_RECORD_EXIST));
        }

        Car car = optionalCar.get();
        Long carId = car.getId();
        Date createTime = car.getCreateTime();

        BeanUtils.copyProperties(carDTOIU, car);
        car.setId(carId);
        car.setCreateTime(createTime);

        Car updatedCar = carRepository.save(car);

        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(updatedCar, carDTO);
        return carDTO;
    }

    @Override
    public String deleteCar(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage("", MessageType.NO_RECORD_EXIST));
        }
        carRepository.deleteById(id);
        return "Araba kaydı başarıyla silindi!" + id;
    }
}
