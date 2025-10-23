package dev.furkankeskin.controller;

import dev.furkankeskin.dto.SaledCarDTO;
import dev.furkankeskin.dto.SaledCarDTOIU;

public interface IRestSaledCarController {

    public RootEntity<SaledCarDTO> buyCar(SaledCarDTOIU saledCarDTOIU);
}
