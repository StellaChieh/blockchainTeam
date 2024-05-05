package com.funpodium.blockchain.service;

import java.time.LocalDateTime;

import com.funpodium.blockchain.model.Exchange;

public interface IExchangeService {

    public Exchange createExchange(Exchange exchange, LocalDateTime curTime);

}
