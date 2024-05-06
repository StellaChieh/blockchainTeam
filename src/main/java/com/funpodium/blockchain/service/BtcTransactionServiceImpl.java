package com.funpodium.blockchain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funpodium.blockchain.exception.AccountDoesNotExistException;
import com.funpodium.blockchain.exception.BalanceNotEnoughException;
import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.model.BTCTransaction;
import com.funpodium.blockchain.repository.IBalanceRepository;
import com.funpodium.blockchain.repository.IBtcTransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class BtcTransactionServiceImpl implements IBtcTransactionService{

    private IBalanceRepository balanceRepository;

    private IBtcTransactionRepository btcTransactionRepository;

    private IQuoteMachine quoteMachine;

    
    @Autowired
    public BtcTransactionServiceImpl(IBalanceRepository balanceRepository, IBtcTransactionRepository btcTransactionRepository,
        IQuoteMachine quoteMachine) {
        this.balanceRepository = balanceRepository;
        this.btcTransactionRepository = btcTransactionRepository;
        this.quoteMachine = quoteMachine;
    }

    @Override
    @Transactional
    public BTCTransaction createBtcTransaction(BTCTransaction btcTransaction, LocalDateTime curTime) {
        Optional<Balance> balanceOpt = this.balanceRepository.findByUserId(btcTransaction.getUserId());
        if(balanceOpt.isEmpty()) {
            throw new AccountDoesNotExistException("userId " + btcTransaction.getUserId() + " does not exists."); 
        }
        Balance balance = balanceOpt.get();
        int usdNewBalance = 0;
        int btcNewBalance = 0;
        int btcPrice = this.quoteMachine.quote(curTime);
        if(btcTransaction.getBtcChange() > 0) { // buy BTC        
            int usdNeeded = btcPrice * btcTransaction.getBtcChange();
            if(usdNeeded > balance.getUsdBalance()) {
                throw new BalanceNotEnoughException("USD balance is not enough."); 
            }
            usdNewBalance = balance.getUsdBalance() - usdNeeded;
            btcNewBalance = balance.getBtcBalance() + btcTransaction.getBtcChange();
            
        } else { // sell BTC
            int btcSell = -1 * btcTransaction.getBtcChange();
            int btcInAccount = balance.getBtcBalance();
            if (btcInAccount < btcSell) {
                throw new BalanceNotEnoughException("BTC balance is not enough."); 
            }
            usdNewBalance = balance.getUsdBalance() + btcPrice * btcSell;
            btcNewBalance = btcInAccount - btcSell;
        }

        balance.setUsdBalance(usdNewBalance);
        balance.setBtcBalance(btcNewBalance);
        this.balanceRepository.save(balance);
        
        btcTransaction.setBtcPrice(btcPrice);
        BTCTransaction savedExchange = this.btcTransactionRepository.save(btcTransaction);
        savedExchange.setUsdBalance(usdNewBalance);
        savedExchange.setBtcBalance(btcNewBalance);
        return savedExchange;
        
    }

    @Override
    public List<BTCTransaction> getBtcTransactionHistory(int userId) {
        return this.btcTransactionRepository.findByUserId(userId);
    }

}
