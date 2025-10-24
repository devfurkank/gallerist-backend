package dev.furkankeskin.controller;

import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;

import java.util.List;

public interface IRestAddressController {

    public RootEntity<AddressDTO> saveAddress(AddressDTOIU addressDTOIU);

    public RootEntity<List<AddressDTO>> getAllAddresses();

    public RootEntity<AddressDTO> updateAddress(Long id, AddressDTOIU addressDTOIU);

    public RootEntity<String> deleteAddress(Long id);
}
