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


@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
	}
    
}
