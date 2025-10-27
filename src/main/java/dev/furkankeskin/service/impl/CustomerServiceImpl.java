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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            AddressDTO addressDTO = new AddressDTO();
            AccountDTO accountDTO = new AccountDTO();

            BeanUtils.copyProperties(customer, customerDTO);
            BeanUtils.copyProperties(customer.getAddress(), addressDTO);
            BeanUtils.copyProperties(customer.getAccount(), accountDTO);

            customerDTO.setAddress(addressDTO);
            customerDTO.setAccount(accountDTO);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTOIU customerDTOIU) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Address> optionalAddress = addressRepository.findById(customerDTOIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(customerDTOIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Account> optionalAccount = accountRepository.findById(customerDTOIU.getAccountId());
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(customerDTOIU.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Customer existingCustomer = optionalCustomer.get();
        existingCustomer.setFirstName(customerDTOIU.getFirstName());
        existingCustomer.setLastName(customerDTOIU.getLastName());
        existingCustomer.setTckn(customerDTOIU.getTckn());
        existingCustomer.setBirthDate(customerDTOIU.getBirthDate());
        existingCustomer.setAddress(optionalAddress.get());
        existingCustomer.setAccount(optionalAccount.get());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        CustomerDTO customerDTO = new CustomerDTO();
        AddressDTO addressDTO = new AddressDTO();
        AccountDTO accountDTO = new AccountDTO();

        BeanUtils.copyProperties(updatedCustomer, customerDTO);
        BeanUtils.copyProperties(updatedCustomer.getAddress(), addressDTO);
        BeanUtils.copyProperties(updatedCustomer.getAccount(), accountDTO);

        customerDTO.setAddress(addressDTO);
        customerDTO.setAccount(accountDTO);

        return customerDTO;
    }

    @Override
    public String deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        customerRepository.deleteById(id);
        return "Müşteri başarıyla silindi!";
    }
}
