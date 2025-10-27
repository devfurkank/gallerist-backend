package dev.furkankeskin.service;

import dev.furkankeskin.dto.GalleristCarDTO;
import dev.furkankeskin.dto.GalleristCarDTOIU;

import java.util.List;

public interface IGalleristCarService {

    public GalleristCarDTO saveGalleristCar(GalleristCarDTOIU galleristCarDTOIU);

    public List<GalleristCarDTO> getAllGalleristCars();

    public GalleristCarDTO updateGalleristCar(Long id, GalleristCarDTOIU galleristCarDTOIU);

    public String deleteGalleristCar(Long id);
}
