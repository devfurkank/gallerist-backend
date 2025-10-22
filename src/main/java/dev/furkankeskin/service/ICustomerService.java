package dev.furkankeskin.service;

import dev.furkankeskin.dto.CustomerDTO;
import dev.furkankeskin.dto.CustomerDTOIU;

public interface ICustomerService {

    public CustomerDTO saveCustomer(CustomerDTOIU customerDTOIU);
}
