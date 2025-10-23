package dev.furkankeskin.service;

import dev.furkankeskin.dto.SaledCarDTO;
import dev.furkankeskin.dto.SaledCarDTOIU;

public interface ISaledCarService {

    public SaledCarDTO buyCar(SaledCarDTOIU saledCarDTOIU);
}
