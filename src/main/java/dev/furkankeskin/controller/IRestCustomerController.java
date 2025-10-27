package dev.furkankeskin.controller;

import dev.furkankeskin.dto.CustomerDTO;
import dev.furkankeskin.dto.CustomerDTOIU;

import java.util.List;

public interface IRestCustomerController {

    public RootEntity<CustomerDTO> saveCustomer(CustomerDTOIU customerDTOIU);

    public RootEntity<List<CustomerDTO>> getAllCustomers();

    public RootEntity<CustomerDTO> updateCustomer(Long id, CustomerDTOIU customerDTOIU);

    public RootEntity<String> deleteCustomer(Long id);
}
