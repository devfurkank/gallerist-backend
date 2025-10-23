package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestCarController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.CarDTO;
import dev.furkankeskin.dto.CarDTOIU;
import dev.furkankeskin.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    @Override
    public RootEntity<List<CarDTO>> getAllCars() {
        return ok(carService.getAllCars());
    }

    @PostMapping("/update/{id}")
    @Override
    public RootEntity<CarDTO> updateCar(@PathVariable Long id, @Valid @RequestBody CarDTOIU carDTOIU) {
        return ok(carService.updateCar(id, carDTOIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteCar(@PathVariable Long id) {
        return ok(carService.deleteCar(id));
    }
}
