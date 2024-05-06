package com.funpodium.blockchain.controller;

import org.springframework.web.bind.annotation.RestController;

import com.funpodium.blockchain.model.Account;
import com.funpodium.blockchain.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final IAccountService accountService;

    
    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = this.accountService.createAccount(account);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}
    
    @DeleteMapping("/accounts/{userId}")
    public ResponseEntity<Account> deleteAccount(@PathVariable String userId) {
        
        // TODO
        return null;
    }
}
