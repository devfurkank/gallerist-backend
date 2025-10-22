package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;
import dev.furkankeskin.model.Account;
import dev.furkankeskin.repository.AccountRepository;
import dev.furkankeskin.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl  implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(AccountDTOIU accountDTOIU) {
        Account account = new Account();
        account.setCreateTime(new Date());

        BeanUtils.copyProperties(accountDTOIU, account);
        return account;
    }

    @Override
    public AccountDTO saveAccount(AccountDTOIU accountDTOIU) {
        AccountDTO accountDTO = new AccountDTO();

        Account savedAccount = accountRepository.save(createAccount(accountDTOIU));
        BeanUtils.copyProperties(savedAccount, accountDTO);
        return accountDTO;
    }
}
