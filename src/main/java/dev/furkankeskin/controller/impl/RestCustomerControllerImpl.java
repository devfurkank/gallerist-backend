package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestCustomerController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.CustomerDTO;
import dev.furkankeskin.dto.CustomerDTOIU;
import dev.furkankeskin.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTOIU customerDTOIU) {
        return ok(customerService.saveCustomer(customerDTOIU));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<CustomerDTO>> getAllCustomers() {
        return ok(customerService.getAllCustomers());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTOIU customerDTOIU) {
        return ok(customerService.updateCustomer(id, customerDTOIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteCustomer(@PathVariable Long id) {
        return ok(customerService.deleteCustomer(id));
    }
}
