package dev.furkankeskin.controller;

import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;

public interface IRestAccountController {

    public RootEntity<AccountDTO> saveAccount(AccountDTOIU accountDTOIU);
}
