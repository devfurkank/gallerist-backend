package dev.furkankeskin.controller;

import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;

import java.util.List;

public interface IRestAccountController {

    public RootEntity<AccountDTO> saveAccount(AccountDTOIU accountDTOIU);

    public RootEntity<List<AccountDTO>> getAllAccounts();

    public RootEntity<AccountDTO> updateAccount(Long id, AccountDTOIU accountDTOIU);

    public RootEntity<String> deleteAccount(Long id);
}
