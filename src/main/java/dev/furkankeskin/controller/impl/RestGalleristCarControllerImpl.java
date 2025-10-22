package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestGalleristCarController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.GalleristCarDTO;
import dev.furkankeskin.dto.GalleristCarDTOIU;
import dev.furkankeskin.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<GalleristCarDTO> saveGalleristCar(@Valid @RequestBody GalleristCarDTOIU galleristCarDTOIU) {
        return ok(galleristCarService.saveGalleristCar(galleristCarDTOIU));
    }
}
