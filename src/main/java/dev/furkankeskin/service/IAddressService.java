package dev.furkankeskin.service;

import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;

public interface IAddressService {

    public AddressDTO saveAddress(AddressDTOIU addressDTOIU);
}
