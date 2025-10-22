package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestCarController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;
import dev.furkankeskin.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<CarDTO> saveCar(@Valid @RequestBody CarDTOIU carDTOIU) {
        return ok(carService.saveCar(carDTOIU));
    }
}
