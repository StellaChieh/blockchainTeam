package com.funpodium.blockchain.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funpodium.blockchain.exception.AccountAlreadyExistsException;
import com.funpodium.blockchain.exception.AccountDoesNotExistException;
import com.funpodium.blockchain.model.Account;
import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.repository.IAccountRepository;
import com.funpodium.blockchain.repository.IBalanceRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements IAccountService{

    
    private final IAccountRepository accountRepository;
    private final IBalanceRepository balanceRepository;

    @Autowired
    public AccountServiceImpl(IAccountRepository accountRepository,
        IBalanceRepository balanceRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    
    public Account createAccount(Account account) {
        Optional<Account> existingAccount = this.accountRepository.findByUsername(account.getUsername());
        if(existingAccount.isPresent()) {
            throw new AccountAlreadyExistsException("username " + account.getUsername() + " already exists.");    
        }
        Account createdAccount = this.accountRepository.save(account);
        Balance newBalance = new Balance(createdAccount.getUserId(), 1000, 0);
        this.balanceRepository.save(newBalance);
        return createdAccount;
    }


    @Override
    @Transactional
    public Account deleteAccount(Account account) {
        Optional<Account> existingAccountOpt = this.accountRepository.findById(account.getUserId());
        if(existingAccountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("userId " + account.getUserId() + " does not exists."); 
        }
        Account existingAccount = existingAccountOpt.get();
        this.accountRepository.delete(existingAccount);
        return null;
    }
}
