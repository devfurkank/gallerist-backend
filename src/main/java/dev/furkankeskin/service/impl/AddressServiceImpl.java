package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.AddressDTOIU;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.model.Address;
import dev.furkankeskin.repository.AddressRepository;
import dev.furkankeskin.service.IAddressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOList = new ArrayList<>();

        for (Address address : addresses) {
            AddressDTO addressDTO = new AddressDTO();
            BeanUtils.copyProperties(address, addressDTO);
            addressDTOList.add(addressDTO);
        }
        return addressDTOList;
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTOIU addressDTOIU) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }
        Address existingAddress = optionalAddress.get();
        existingAddress.setCity(addressDTOIU.getCity());
        existingAddress.setStreet(addressDTOIU.getStreet());
        existingAddress.setDistrict(addressDTOIU.getDistrict());
        existingAddress.setNeighborhood(addressDTOIU.getNeighborhood());

        Address updatedAddress = addressRepository.save(existingAddress);

        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(updatedAddress, addressDTO);
        return addressDTO;
    }

    @Override
    public String deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }
        addressRepository.deleteById(id);
        return "Adres başarıyla silindi!";
    }
}
