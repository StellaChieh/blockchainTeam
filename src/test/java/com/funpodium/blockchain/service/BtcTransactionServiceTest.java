package com.funpodium.blockchain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.funpodium.blockchain.exception.BalanceNotEnoughException;
import com.funpodium.blockchain.model.BTCTransaction;
import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.repository.IBalanceRepository;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application-test.properties")
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

    @Test
    void user_can_see_btc_transaction_history() {
        // save old balance
        int userId = 5;
        int oldUsdBalance = 1000;
        int oldBtcBalance = 3;
        Balance oldBalance = new Balance(userId, oldUsdBalance, oldBtcBalance);
        this.balanceRepository.save(oldBalance);

        // buy btc
        int btcChange1 = 2;
        BTCTransaction btcTransaction1 = new BTCTransaction(userId, btcChange1);
        LocalDateTime curTime1 = LocalDateTime.now();
        BTCTransaction proceedBtcTransaction1 = this.btcTransactionService.createBtcTransaction(btcTransaction1, curTime1);

        // sell btc
        int btcChange2 = -2;
        BTCTransaction btcTransaction2 = new BTCTransaction(userId, btcChange2);
        LocalDateTime curTime2 = LocalDateTime.now();
        BTCTransaction proceedBtcTransaction2 = this.btcTransactionService.createBtcTransaction(btcTransaction2, curTime2);

        List<BTCTransaction> history = this.btcTransactionService.getBtcTransactionHistory(userId);
        assertEquals(2, history.size());
        
        // assert buy transaction
        BTCTransaction b1 = history.get(0);
        assertNotEquals(0, b1.getTransactionId());
        assertEquals(userId, b1.getUserId());
        assertEquals(btcChange1, b1.getBtcChange());
        assertEquals(this.quoteMachine.quote(curTime1), b1.getBtcPrice());
        assertEquals(proceedBtcTransaction1.getUsdBalance(), b1.getUsdBalance());
        assertEquals(proceedBtcTransaction1.getBtcBalance(), b1.getBtcBalance());

        // assert sell transaction
        BTCTransaction b2 = history.get(1);
        assertNotEquals(0, b2.getTransactionId());
        assertEquals(userId, b2.getUserId());
        assertEquals(btcChange2, b2.getBtcChange());
        assertEquals(this.quoteMachine.quote(curTime2), b2.getBtcPrice());
        assertEquals(proceedBtcTransaction2.getUsdBalance(), b2.getUsdBalance());
        assertEquals(proceedBtcTransaction2.getBtcBalance(), b2.getBtcBalance());
        assertEquals(3, b2.getBtcBalance());
        assertTrue(b2.getUsdBalance() > 0);
    }

}
