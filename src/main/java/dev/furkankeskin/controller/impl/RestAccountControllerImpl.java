package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestAccountController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;
import dev.furkankeskin.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
