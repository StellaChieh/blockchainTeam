package com.fonpodium.blockchain.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fonpodium.blockchain.model.Account;
import com.fonpodium.blockchain.service.AccountService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        System.out.println(account);
		Optional<Account> createdAccount = accountService.createAccount(account);
		
		return createdAccount.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
	}

    
}
