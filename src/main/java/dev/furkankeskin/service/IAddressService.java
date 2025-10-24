package dev.furkankeskin.service;

import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;

import java.util.List;

public interface IAddressService {

    public AddressDTO saveAddress(AddressDTOIU addressDTOIU);

    public List<AddressDTO> getAllAddresses();

    public AddressDTO updateAddress(Long id, AddressDTOIU addressDTOIU);

    public String deleteAddress(Long id);
}
