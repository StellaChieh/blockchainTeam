package com.funpodium.blockchain.controller;

import org.springframework.web.bind.annotation.RestController;

import com.funpodium.blockchain.exception.ErrorResponse;
import com.funpodium.blockchain.model.Account;
import com.funpodium.blockchain.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collections;
import java.util.Map;

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
@Tag(name = "Account", description = "Manage an account.")
public class AccountController {

    private static final String createAccountSuccessExample = 
        "{\n" +
        "    \"userId\": 1,\n" +
        "    \"username\": \"Mike\",\n" +
        "    \"email\": \"myEmail@gmail.com\"\n" +
        "}";
    
    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Operation (summary = "Create a new account.",
                description = "username can not be duplicated.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Ok", 
            content = @Content( 
                        mediaType = "application/json", 
                        schema = @Schema(implementation = Account.class),
                        examples = @ExampleObject(value=createAccountSuccessExample)
                    )),
        @ApiResponse(responseCode = "409", description = "username already exists.",
            content = {@Content( mediaType = "application/json", 
                        schema = @Schema(implementation = ErrorResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
            content = {@Content( mediaType = "application/json", 
                        schema = @Schema(implementation = ErrorResponse.class)) })}
    )
    @PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = this.accountService.createAccount(account);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}
    
    @Operation (summary = "Delete an account.",
                description = "User can delete his own account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", 
            content = {@Content( mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "Account does not exist.",
            content = {@Content( mediaType = "application/json", 
                        schema = @Schema(implementation = ErrorResponse.class)) }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error.",
            content = {@Content( mediaType = "application/json", 
                        schema = @Schema(implementation = ErrorResponse.class)) })}
    )
    @DeleteMapping("/accounts/{userId}")
    public ResponseEntity<Map<String, String>> deleteAccount(@PathVariable String userId) {
        this.accountService.deleteAccount(Integer.valueOf(userId));
        return new ResponseEntity<>(Collections.singletonMap("message", "Account " + userId + " is been deleted.")
                    , HttpStatus.OK);
    }
}
