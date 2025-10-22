package dev.furkankeskin.controller;

import dev.furkankeskin.dto.CustomerDTO;
import dev.furkankeskin.dto.CustomerDTOIU;

public interface IRestCustomerController {

    public RootEntity<CustomerDTO> saveCustomer(CustomerDTOIU customerDTOIU);
}
