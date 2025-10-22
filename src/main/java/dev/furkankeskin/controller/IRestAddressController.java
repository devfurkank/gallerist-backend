package dev.furkankeskin.controller;

import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;

public interface IRestAddressController {

    public RootEntity<AddressDTO> saveAddress(AddressDTOIU addressDTOIU);
}
