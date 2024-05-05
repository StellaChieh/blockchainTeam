package com.funpodium.blockchain.controller;

import java.time.LocalDateTime;

import com.funpodium.blockchain.service.IExchangeService;
import com.funpodium.blockchain.model.Exchange;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1")
public class BTCController {

    private final IExchangeService exchangeService;

    @Autowired
    public BTCController(IExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping("/btcs")
    public ResponseEntity<Exchange> exchangeBTC(@RequestBody Exchange exchange) {
        Exchange proceedExchange = this.exchangeService.createExchange(exchange, LocalDateTime.now());
        
        return new ResponseEntity<>(proceedExchange, HttpStatus.CREATED);
    }
    
    
}
