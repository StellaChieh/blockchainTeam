package com.funpodium.blockchain.controller;

import org.springframework.web.bind.annotation.RestController;

import com.funpodium.blockchain.model.Account;
import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.service.IAccountService;
import com.funpodium.blockchain.service.IBalanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final IAccountService accountService;

    private final IBalanceService balanceService;
    
    @Autowired
    public AccountController(IAccountService accountService, IBalanceService balanceService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = this.accountService.createAccount(account);
        Balance newBalance = new Balance(createdAccount.getUserId(), 1000, 0);
        this.balanceService.createBalance(newBalance);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}
    
}
