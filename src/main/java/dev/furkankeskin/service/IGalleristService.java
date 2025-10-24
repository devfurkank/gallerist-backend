package dev.furkankeskin.service;

import dev.furkankeskin.dto.GalleristDTO;
import dev.furkankeskin.dto.GalleristDTOIU;

import java.util.List;

public interface IGalleristService {

    public GalleristDTO saveGallerist(GalleristDTOIU galleristDTOIU);

    public List<GalleristDTO> getAllGallerists();

    public GalleristDTO updateGallerist(Long id, GalleristDTOIU galleristDTOIU);

    public String deleteGallerist(Long id);
}
