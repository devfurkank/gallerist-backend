package dev.furkankeskin.controller;

import dev.furkankeskin.dto.GalleristCarDTO;
import dev.furkankeskin.dto.GalleristCarDTOIU;

import java.util.List;

public interface IRestGalleristCarController {

    public RootEntity<GalleristCarDTO> saveGalleristCar(GalleristCarDTOIU galleristCarDTOIU);

    public RootEntity<List<GalleristCarDTO>>  getAllGalleristCars();

    public RootEntity<GalleristCarDTO> updateGalleristCar(Long id, GalleristCarDTOIU galleristCarDTOIU);

    public RootEntity<String> deleteGalleristCar(Long id);
}
