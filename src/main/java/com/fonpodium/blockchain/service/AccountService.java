package com.fonpodium.blockchain.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonpodium.blockchain.model.Account;
import com.fonpodium.blockchain.repository.IAccountRepository;

@Service
public class AccountService {


    private final IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository repo) {
        this.accountRepository = repo;
    }

    private boolean usernameExists(String username) {
        return false;
    }

    public Optional<Account> createAccount(Account account) {
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if(existingAccount.isPresent()) {
            return Optional.empty();
        }
        System.out.println("Here");
        return Optional.of(accountRepository.save(account));

    }
}
