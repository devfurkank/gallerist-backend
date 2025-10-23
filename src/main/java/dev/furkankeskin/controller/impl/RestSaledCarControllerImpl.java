package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestSaledCarController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.SaledCarDTO;
import dev.furkankeskin.dto.SaledCarDTOIU;
import dev.furkankeskin.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {

    @Autowired
    private ISaledCarService saledCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<SaledCarDTO> buyCar(@Valid @RequestBody SaledCarDTOIU saledCarDTOIU) {
        return ok(saledCarService.buyCar(saledCarDTOIU));
    }
}
