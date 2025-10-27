package dev.furkankeskin.service;

import dev.furkankeskin.dto.SaledCarDTO;
import dev.furkankeskin.dto.SaledCarDTOIU;

import java.util.List;

public interface ISaledCarService {

    public SaledCarDTO buyCar(SaledCarDTOIU saledCarDTOIU);

    public List<SaledCarDTO> getAllSaledCars();

    public SaledCarDTO updateSaledCar(Long id, SaledCarDTOIU saledCarDTOIU);

    public String deleteSaledCar(Long id);
}
