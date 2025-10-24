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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Override
    public List<GalleristDTO> getAllGallerists() {
        List<Gallerist> gallerists = galleristRepository.findAll();
        List<GalleristDTO> galleristDTOList = new ArrayList<>();
        for (Gallerist gallerist : gallerists) {
            GalleristDTO galleristDTO = new GalleristDTO();
            AddressDTO addressDTO = new AddressDTO();

            BeanUtils.copyProperties(gallerist, galleristDTO);
            BeanUtils.copyProperties(gallerist.getAddress(), addressDTO);

            galleristDTO.setAddress(addressDTO);
            galleristDTOList.add(galleristDTO);
        }
        return galleristDTOList;
    }

    @Override
    public GalleristDTO updateGallerist(Long id, GalleristDTOIU galleristDTOIU) {
        Optional<Gallerist> optionalGallerist = galleristRepository.findById(id);
        if (optionalGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(),  MessageType.NO_RECORD_EXIST));
        }
        Optional<Address> optionalAddress = addressRepository.findById(galleristDTOIU.getAddressId());
        if (optionalAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(galleristDTOIU.getAddressId().toString(),  MessageType.NO_RECORD_EXIST));
        }
        Gallerist existingGallerist = optionalGallerist.get();
        existingGallerist.setFirstName(galleristDTOIU.getFirstName());
        existingGallerist.setLastName(galleristDTOIU.getLastName());
        existingGallerist.setAddress(optionalAddress.get());

        Gallerist updatedGallerist = galleristRepository.save(existingGallerist);

        GalleristDTO galleristDTO = new GalleristDTO();
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(updatedGallerist, galleristDTO);
        BeanUtils.copyProperties(updatedGallerist.getAddress(), addressDTO);

        galleristDTO.setAddress(addressDTO);
        return galleristDTO;
    }

    @Override
    public String deleteGallerist(Long id) {
        Optional<Gallerist> optionalGallerist = galleristRepository.findById(id);
        if (optionalGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(),  MessageType.NO_RECORD_EXIST));
        }
        galleristRepository.deleteById(id);
        return "Galerici başarıyla silindi!";
    }
}
