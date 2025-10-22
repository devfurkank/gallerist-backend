package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.GalleristDTO;
import dev.furkankeskin.dto.GalleristDTOIU;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.model.Address;
import dev.furkankeskin.model.Gallerist;
import dev.furkankeskin.repository.AddressRepository;
import dev.furkankeskin.repository.GalleristRepository;
import dev.furkankeskin.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(GalleristDTOIU galleristDTOIU) {
        Optional<Address>  optionalAddress = addressRepository.findById(galleristDTOIU.getAddressId());
        if (optionalAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(galleristDTOIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(galleristDTOIU, gallerist);
        gallerist.setAddress(optionalAddress.get());
        return gallerist;
    }

    @Override
    public GalleristDTO saveGallerist(GalleristDTOIU galleristDTOIU) {
        GalleristDTO galleristDTO = new GalleristDTO();
        AddressDTO addressDTO = new AddressDTO();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(galleristDTOIU));

        BeanUtils.copyProperties(savedGallerist, galleristDTO);
        BeanUtils.copyProperties(savedGallerist.getAddress(), addressDTO);

        galleristDTO.setAddress(addressDTO);
        return galleristDTO;
    }
}
