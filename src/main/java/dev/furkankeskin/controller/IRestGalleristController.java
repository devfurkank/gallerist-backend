package dev.furkankeskin.controller;

import dev.furkankeskin.dto.GalleristDTO;
import dev.furkankeskin.dto.GalleristDTOIU;

public interface IRestGalleristController {

    public RootEntity<GalleristDTO> saveGallerist(GalleristDTOIU galleristDTOIU);
}
