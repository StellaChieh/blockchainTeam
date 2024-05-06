package com.funpodium.blockchain.service;

import java.time.LocalDateTime;

import com.funpodium.blockchain.model.BTCTransaction;

public interface IBtcTransactionService {

    public BTCTransaction createBtcTransaction(BTCTransaction exchange, LocalDateTime curTime);

}
