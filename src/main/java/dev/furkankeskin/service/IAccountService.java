package dev.furkankeskin.service;

import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;

public interface IAccountService {

    public AccountDTO saveAccount(AccountDTOIU accountDTOIU);
}
