package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestSaledCarController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.SaledCarDTO;
import dev.furkankeskin.dto.SaledCarDTOIU;
import dev.furkankeskin.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    @Override
    public RootEntity<List<SaledCarDTO>> getAllSaledCars() {
        return ok(saledCarService.getAllSaledCars());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<SaledCarDTO> updateSaledCar(@PathVariable Long id, @Valid @RequestBody SaledCarDTOIU saledCarDTOIU) {
        return ok(saledCarService.updateSaledCar(id, saledCarDTOIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteSaledCar(@PathVariable Long id) {
        return ok(saledCarService.deleteSaledCar(id));
    }
}
