package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestGalleristCarController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.GalleristCarDTO;
import dev.furkankeskin.dto.GalleristCarDTOIU;
import dev.furkankeskin.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    @Override
    public RootEntity<List<GalleristCarDTO>> getAllGalleristCars() {
        return ok(galleristCarService.getAllGalleristCars());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<GalleristCarDTO> updateGalleristCar(@PathVariable Long id, @Valid @RequestBody GalleristCarDTOIU galleristCarDTOIU) {
        return ok(galleristCarService.updateGalleristCar(id, galleristCarDTOIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteGalleristCar(@PathVariable Long id) {
        return ok(galleristCarService.deleteGalleristCar(id));
    }
}
