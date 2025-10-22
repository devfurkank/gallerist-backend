package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestAddressController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;
import dev.furkankeskin.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/save")
    @Override
    public RootEntity<AddressDTO> saveAddress(@Valid @RequestBody AddressDTOIU addressDTOIU) {
        return ok(addressService.saveAddress(addressDTOIU));
    }
}
