package dev.furkankeskin.service;

import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;

import java.util.List;

public interface IAccountService {

    public AccountDTO saveAccount(AccountDTOIU accountDTOIU);

    public List<AccountDTO> getAllAccounts();

    public AccountDTO updateAccount(Long id, AccountDTOIU accountDTOIU);

    public String deleteAccount(Long id);
}
