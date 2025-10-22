package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;
import dev.furkankeskin.model.Address;
import dev.furkankeskin.repository.AddressRepository;
import dev.furkankeskin.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(AddressDTOIU addressDTOIU) {
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(addressDTOIU, address);
        return address;
    }

    @Override
    public AddressDTO saveAddress(AddressDTOIU addressDTOIU) {
        AddressDTO addressDTO = new AddressDTO();
        Address savedAddress = addressRepository.save(createAddress(addressDTOIU));
        BeanUtils.copyProperties(savedAddress, addressDTO);
        return addressDTO;
    }
}
