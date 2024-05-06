package com.funpodium.blockchain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.funpodium.blockchain.exception.BalanceNotEnoughException;
import com.funpodium.blockchain.model.BTCTransaction;
import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.repository.IBalanceRepository;

@SpringBootTest
public class BtcTransactionServiceTest {

    @Autowired
    private IBtcTransactionService btcTransactionService;

    @Autowired
    private IBalanceRepository balanceRepository;

    @Autowired
    private IQuoteMachine quoteMachine;

    @Test
	void contextLoads() throws Exception{
		assertThat(btcTransactionService).isNotNull();
        assertThat(balanceRepository).isNotNull();
	}

    @Test
    void user_can_buy_btc() {
        // save old balance
        int userId = 1;
        int oldUsdBalance = 1000;
        int oldBtcBalance = 3;
        Balance oldBalance = new Balance(userId, oldUsdBalance, oldBtcBalance);
        this.balanceRepository.save(oldBalance);

        // make btc transaction
        int btcChange = 2;
        BTCTransaction btcTransaction = new BTCTransaction(userId, btcChange);
        LocalDateTime curTime = LocalDateTime.now();
        BTCTransaction proceedBtcTransaction = this.btcTransactionService.createBtcTransaction(btcTransaction, curTime);

        assertNotEquals(0, proceedBtcTransaction.getTransactionId());
        assertEquals(userId, proceedBtcTransaction.getUserId());
        assertEquals(btcChange, proceedBtcTransaction.getBtcChange());
        assertEquals(this.quoteMachine.quote(curTime), proceedBtcTransaction.getBtcPrice());
        assertEquals(oldUsdBalance - btcChange * this.quoteMachine.quote(curTime), proceedBtcTransaction.getUsdBalance());
        assertEquals(oldBtcBalance + btcChange, proceedBtcTransaction.getBtcBalance());
    }

    @Test
    void user_can_sell_btc() {
        // save old balance
        int userId = 2;
        int oldUsdBalance = 1500;
        int oldBtcBalance = 5;
        Balance oldBalance = new Balance(userId, oldUsdBalance, oldBtcBalance);
        this.balanceRepository.save(oldBalance);

        // make btc transaction
        int btcChange = -4;
        BTCTransaction btcTransaction = new BTCTransaction(userId, btcChange);
        LocalDateTime curTime = LocalDateTime.now();
        BTCTransaction proceedBtcTransaction = this.btcTransactionService.createBtcTransaction(btcTransaction, curTime);

        assertNotEquals(0, proceedBtcTransaction.getTransactionId());
        assertEquals(userId, proceedBtcTransaction.getUserId());
        assertEquals(btcChange, proceedBtcTransaction.getBtcChange());
        assertEquals(this.quoteMachine.quote(curTime), proceedBtcTransaction.getBtcPrice());
        assertEquals(oldUsdBalance + Math.abs(btcChange * this.quoteMachine.quote(curTime)), proceedBtcTransaction.getUsdBalance());
        assertEquals(oldBtcBalance + btcChange, proceedBtcTransaction.getBtcBalance());
    }

    @Test
    void user_can_not_buy_btc_when_usd_not_enough() {
        // save old balance
        int userId = 3;
        int oldUsdBalance = 0;
        int oldBtcBalance = 5;
        Balance oldBalance = new Balance(userId, oldUsdBalance, oldBtcBalance);
        this.balanceRepository.save(oldBalance);

        // make new transaction
        int btcChange = 10;
        BTCTransaction btcTransaction = new BTCTransaction(userId, btcChange);

        assertThrowsExactly( BalanceNotEnoughException.class, 
            () -> this.btcTransactionService.createBtcTransaction(btcTransaction, LocalDateTime.now()));
    }

    @Test
    void user_can_not_sell_btc_when_btc_not_enough() {
        // save old balance
        int userId = 4;
        int oldUsdBalance = 146;
        int oldBtcBalance = 0;
        Balance oldBalance = new Balance(userId, oldUsdBalance, oldBtcBalance);
        this.balanceRepository.save(oldBalance);

        // make new transaction
        int btcChange = -10;
        BTCTransaction btcTransaction = new BTCTransaction(userId, btcChange);

        assertThrowsExactly( BalanceNotEnoughException.class, 
            () -> this.btcTransactionService.createBtcTransaction(btcTransaction, LocalDateTime.now()));
    }

}
