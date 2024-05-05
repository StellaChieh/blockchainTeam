package com.fonpodium.blockchain.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonpodium.blockchain.exception.AccountAlreadyExistsException;
import com.fonpodium.blockchain.model.Account;
import com.fonpodium.blockchain.repository.IAccountRepository;

@Service
public class AccountService {


    private final IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository repo) {
        this.accountRepository = repo;
    }

    
    public Account createAccount(Account account) {
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if(existingAccount.isPresent()) {
            throw new AccountAlreadyExistsException("username " + account.getUsername() + " already exists.");    
        }
        
        return accountRepository.save(account);

    }
}
