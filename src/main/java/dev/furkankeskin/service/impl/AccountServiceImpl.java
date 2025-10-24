package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AccountDTO;
import dev.furkankeskin.dto.AccountDTOIU;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.model.Account;
import dev.furkankeskin.repository.AccountRepository;
import dev.furkankeskin.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOList = new ArrayList<>();

        for (Account account : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            BeanUtils.copyProperties(account, accountDTO);
            accountDTOList.add(accountDTO);
        }
        return accountDTOList;
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTOIU accountDTOIU) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        Account existingAccount = optionalAccount.get();
        existingAccount.setAccountNo(accountDTOIU.getAccountNo());
        existingAccount.setIban(accountDTOIU.getIban());
        existingAccount.setAmount(accountDTOIU.getAmount());
        existingAccount.setCurrencyType(accountDTOIU.getCurrencyType());

        Account updatedAccount = accountRepository.save(existingAccount);

        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(updatedAccount, accountDTO);
        return accountDTO;
    }

    @Override
    public String deleteAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        accountRepository.deleteById(id);
        return "Hesap başarıyla silindi!";
    }
}
