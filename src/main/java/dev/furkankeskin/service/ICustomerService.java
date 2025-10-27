package dev.furkankeskin.service;

import dev.furkankeskin.dto.CustomerDTO;
import dev.furkankeskin.dto.CustomerDTOIU;

import java.util.List;

public interface ICustomerService {

    public CustomerDTO saveCustomer(CustomerDTOIU customerDTOIU);

    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO updateCustomer(Long id, CustomerDTOIU customerDTOIU);

    public String deleteCustomer(Long id);
}
