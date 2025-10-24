package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestAccountController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;
import dev.furkankeskin.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @Override
    public RootEntity<AccountDTO> saveAccount(@Valid @RequestBody AccountDTOIU accountDTOIU) {
        return ok(accountService.saveAccount(accountDTOIU));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<AccountDTO>> getAllAccounts() {
        return ok(accountService.getAllAccounts());
    }

    @PutMapping("/update/{id}")
    @Override
    public RootEntity<AccountDTO> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDTOIU accountDTOIU) {
        return ok(accountService.updateAccount(id, accountDTOIU));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteAccount(@PathVariable Long id) {
        return ok(accountService.deleteAccount(id));
    }
}
