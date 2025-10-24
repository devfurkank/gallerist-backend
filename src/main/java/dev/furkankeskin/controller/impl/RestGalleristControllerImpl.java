package dev.furkankeskin.controller.impl;


import dev.furkankeskin.controller.IRestGalleristController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.GalleristDTO;
import dev.furkankeskin.dto.GalleristDTOIU;
import dev.furkankeskin.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    @Override
    public RootEntity<List<GalleristDTO>> getAllGallerists() {
        return ok(galleristService.getAllGallerists());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<GalleristDTO> updateGallerist(@PathVariable Long id,@Valid @RequestBody GalleristDTOIU galleristDTOIU) {
        return ok(galleristService.updateGallerist(id, galleristDTOIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteGallerist(@PathVariable Long id) {
        return ok(galleristService.deleteGallerist(id));
    }
}
