package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AddressDTO;
import dev.furkankeskin.dto.CustomerDTO;
import dev.furkankeskin.dto.CustomerDTOIU;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.model.Account;
import dev.furkankeskin.model.Address;
import dev.furkankeskin.model.Customer;
import dev.furkankeskin.repository.AccountRepository;
import dev.furkankeskin.repository.AddressRepository;
import dev.furkankeskin.repository.CustomerRepository;
import dev.furkankeskin.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(CustomerDTOIU customerDTOIU) {
        Optional<Address> optionalAddress = addressRepository.findById(customerDTOIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(customerDTOIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
        }
        Optional<Account> optionalAccount = accountRepository.findById(customerDTOIU.getAccountId());
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(customerDTOIU.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(customerDTOIU, customer);
        customer.setAddress(optionalAddress.get());
        customer.setAccount(optionalAccount.get());

        return customer;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTOIU customerDTOIU) {
        CustomerDTO customerDTO = new CustomerDTO();
        AddressDTO addressDTO = new AddressDTO();
        AccountDTO accountDTO = new AccountDTO();

        Customer savedCustomer = customerRepository.save(createCustomer(customerDTOIU));

        BeanUtils.copyProperties(savedCustomer, customerDTO);
        BeanUtils.copyProperties(savedCustomer.getAddress(), addressDTO);
        BeanUtils.copyProperties(savedCustomer.getAccount(), accountDTO);

        customerDTO.setAddress(addressDTO);
        customerDTO.setAccount(accountDTO);

        return customerDTO;
    }
}
