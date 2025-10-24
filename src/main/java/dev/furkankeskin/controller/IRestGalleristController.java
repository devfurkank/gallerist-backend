package dev.furkankeskin.controller;

import dev.furkankeskin.dto.GalleristDTO;
import dev.furkankeskin.dto.GalleristDTOIU;

import java.util.List;

public interface IRestGalleristController {

    public RootEntity<GalleristDTO> saveGallerist(GalleristDTOIU galleristDTOIU);

    public RootEntity<List<GalleristDTO>> getAllGallerists();

    public RootEntity<GalleristDTO> updateGallerist(Long id, GalleristDTOIU galleristDTOIU);

    public RootEntity<String> deleteGallerist(Long id);
}
