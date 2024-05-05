package com.funpodium.blockchain.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funpodium.blockchain.exception.AccountAlreadyExistsException;
import com.funpodium.blockchain.model.Account;
import com.funpodium.blockchain.repository.IAccountRepository;

@Service
public class AccountServiceImpl implements IAccountService{

    
    private final IAccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(IAccountRepository repo) {
        this.accountRepository = repo;
    }

    
    public Account createAccount(Account account) {
        Optional<Account> existingAccount = this.accountRepository.findByUsername(account.getUsername());
        if(existingAccount.isPresent()) {
            throw new AccountAlreadyExistsException("username " + account.getUsername() + " already exists.");    
        }
        
        return this.accountRepository.save(account);

    }
}
