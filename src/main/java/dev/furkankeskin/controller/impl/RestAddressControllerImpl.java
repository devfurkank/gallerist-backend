package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestAddressController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;
import dev.furkankeskin.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    @Override
    public RootEntity<List<AddressDTO>> getAllAddresses() {
        return ok(addressService.getAllAddresses());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<AddressDTO> updateAddress(@PathVariable Long id,@Valid @RequestBody AddressDTOIU addressDTOIU) {
        return ok(addressService.updateAddress(id, addressDTOIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteAddress(@PathVariable Long id) {
        return ok(addressService.deleteAddress(id));
    }
}
