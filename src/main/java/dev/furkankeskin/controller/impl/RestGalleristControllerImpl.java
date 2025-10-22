package dev.furkankeskin.controller.impl;


import dev.furkankeskin.controller.IRestGalleristController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.GalleristDTO;
import dev.furkankeskin.dto.GalleristDTOIU;
import dev.furkankeskin.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping("/save")
    @Override
    public RootEntity<GalleristDTO> saveGallerist(@Valid @RequestBody GalleristDTOIU galleristDTOIU) {
        return ok(galleristService.saveGallerist(galleristDTOIU));
    }
}
