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
import com.funpodium.blockchain.repository.IBtcTransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements IAccountService{

    
    private final IAccountRepository accountRepository;
    private final IBalanceRepository balanceRepository;
    private final IBtcTransactionRepository btcTransactionRepository;

    @Autowired
    public AccountServiceImpl(IAccountRepository accountRepository,
        IBalanceRepository balanceRepository, IBtcTransactionRepository btcTransactionRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
        this.btcTransactionRepository = btcTransactionRepository;
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
    public void deleteAccount(int userId) {
        Optional<Account> existingAccountOpt = this.accountRepository.findById(userId);
        if(existingAccountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("userId " + userId + " does not exists."); 
        }
        Account existingAccount = existingAccountOpt.get();
        this.accountRepository.delete(existingAccount);
        this.balanceRepository.deleteById(userId);
        this.btcTransactionRepository.deleteByUserId(userId);
    }
}
