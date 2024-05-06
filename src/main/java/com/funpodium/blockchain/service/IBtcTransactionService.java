package com.funpodium.blockchain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.funpodium.blockchain.model.BTCTransaction;

public interface IBtcTransactionService {

    public BTCTransaction createBtcTransaction(BTCTransaction exchange, LocalDateTime curTime);

    public List<BTCTransaction> getBtcTransactionHistory(int userId);

}
