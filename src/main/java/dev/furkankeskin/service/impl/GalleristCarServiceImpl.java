package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.*;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.model.Car;
import dev.furkankeskin.model.Gallerist;
import dev.furkankeskin.model.GalleristCar;
import dev.furkankeskin.repository.CarRepository;
import dev.furkankeskin.repository.GalleristCarRepository;
import dev.furkankeskin.repository.GalleristRepository;
import dev.furkankeskin.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(GalleristCarDTOIU galleristCarDTOIU) {
        Optional<Gallerist> optionalGallerist = galleristRepository.findById(galleristCarDTOIU.getGalleristId());
        if (optionalGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(galleristCarDTOIU.getGalleristId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Car>  optionalCar = carRepository.findById(galleristCarDTOIU.getCarId());
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(galleristCarDTOIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optionalGallerist.get());
        galleristCar.setCar(optionalCar.get());
        return galleristCar;
    }

    @Override
    public GalleristCarDTO saveGalleristCar(GalleristCarDTOIU galleristCarDTOIU) {
        GalleristCarDTO galleristCarDTO = new GalleristCarDTO();
        GalleristDTO  galleristDTO = new GalleristDTO();
        CarDTO carDTO = new CarDTO();
        AddressDTO addressDTO = new AddressDTO();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(galleristCarDTOIU));

        BeanUtils.copyProperties(savedGalleristCar, galleristCarDTO);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), galleristDTO);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), carDTO);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), addressDTO);

        galleristDTO.setAddress(addressDTO);
        galleristCarDTO.setGallerist(galleristDTO);
        galleristCarDTO.setCar(carDTO);
        return galleristCarDTO;


    }

    @Override
    public List<GalleristCarDTO> getAllGalleristCars() {
        List<GalleristCar> galleristCars = galleristCarRepository.findAll();
        List<GalleristCarDTO> galleristCarDTOList = new ArrayList<>();
        for (GalleristCar galleristCar : galleristCars) {
            GalleristCarDTO galleristCarDTO = new GalleristCarDTO();
            GalleristDTO  galleristDTO = new GalleristDTO();
            CarDTO carDTO = new CarDTO();
            AddressDTO addressDTO = new AddressDTO();

            BeanUtils.copyProperties(galleristCar, galleristCarDTO);
            BeanUtils.copyProperties(galleristCar.getGallerist(), galleristDTO);
            BeanUtils.copyProperties(galleristCar.getCar(), carDTO);
            BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), addressDTO);

            galleristDTO.setAddress(addressDTO);
            galleristCarDTO.setGallerist(galleristDTO);
            galleristCarDTO.setCar(carDTO);
            galleristCarDTOList.add(galleristCarDTO);
        }
        return galleristCarDTOList;
    }

    @Override
    public GalleristCarDTO updateGalleristCar(Long id, GalleristCarDTOIU galleristCarDTOIU) {
        Optional<GalleristCar> optionalGalleristCar = galleristCarRepository.findById(id);
        if (optionalGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Gallerist> optionalGallerist = galleristRepository.findById(galleristCarDTOIU.getGalleristId());
        if (optionalGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(galleristCarDTOIU.getGalleristId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Car> optionalCar = carRepository.findById(galleristCarDTOIU.getCarId());
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(galleristCarDTOIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));
        }

        GalleristCar existingGalleristCar = optionalGalleristCar.get();
        existingGalleristCar.setGallerist(optionalGallerist.get());
        existingGalleristCar.setCar(optionalCar.get());

        GalleristCar updatedGalleristCar = galleristCarRepository.save(existingGalleristCar);

        GalleristCarDTO galleristCarDTO = new GalleristCarDTO();
        GalleristDTO  galleristDTO = new GalleristDTO();
        CarDTO carDTO = new CarDTO();
        AddressDTO addressDTO = new AddressDTO();

        BeanUtils.copyProperties(updatedGalleristCar, galleristCarDTO);
        BeanUtils.copyProperties(updatedGalleristCar.getGallerist(), galleristDTO);
        BeanUtils.copyProperties(updatedGalleristCar.getCar(), carDTO);
        BeanUtils.copyProperties(updatedGalleristCar.getGallerist().getAddress(), addressDTO);

        galleristDTO.setAddress(addressDTO);
        galleristCarDTO.setGallerist(galleristDTO);
        galleristCarDTO.setCar(carDTO);
        return galleristCarDTO;
    }

    @Override
    public String deleteGalleristCar(Long id) {
        Optional<GalleristCar> optionalGalleristCar = galleristCarRepository.findById(id);
        if (optionalGalleristCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        galleristCarRepository.deleteById(id);
        return "Galerici-Araba kaydı başarıyla silindi!";
    }
}
