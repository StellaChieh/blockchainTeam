package com.funpodium.blockchain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funpodium.blockchain.exception.AccountDoesNotExistException;
import com.funpodium.blockchain.exception.BalanceNotEnoughException;
import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.model.Exchange;
import com.funpodium.blockchain.repository.IBalanceRepository;
import com.funpodium.blockchain.repository.IExchangeRepository;

@Service
public class ExchangeServiceImpl implements IExchangeService{

    private IBalanceRepository balanceRepository;

    private IExchangeRepository exchangeRepository;

    private IQuoteMachine quoteMachine;

    
    @Autowired
    public ExchangeServiceImpl(IBalanceRepository balanceRepository, IExchangeRepository exchangeRepository,
        IQuoteMachine quoteMachine) {
        this.balanceRepository = balanceRepository;
        this.exchangeRepository = exchangeRepository;
        this.quoteMachine = quoteMachine;
    }

    @Override
    public Exchange createExchange(Exchange exchange, LocalDateTime curTime) {
        Optional<Balance> balanceOpt = this.balanceRepository.findByUserId(exchange.getUserId());
        if(balanceOpt.isEmpty()) {
            throw new AccountDoesNotExistException("userId " + exchange.getUserId() + " does not exists."); 
        }
        Balance balance = balanceOpt.get();
        int usdNewBalance = 0;
        int btcNewBalance = 0;
        int btcPrice = this.quoteMachine.quote(curTime);
        if(exchange.isExchangeType()) { // buy BTC        
            int usdNeeded = btcPrice * exchange.getBtcChange();
            if(usdNeeded > balance.getUsdBalance()) {
                throw new BalanceNotEnoughException("USD balance is not enough."); 
            }
            usdNewBalance = balance.getUsdBalance() - usdNeeded;
            btcNewBalance = balance.getBtcBalance() + exchange.getBtcChange();
            
        } else { // sell BTC
            int btcInAccount = balance.getBtcBalance();
            if (btcInAccount < exchange.getBtcChange()) {
                throw new BalanceNotEnoughException("BTC balance is not enough."); 
            }
            usdNewBalance = balance.getUsdBalance() + btcPrice * exchange.getBtcChange();
            btcNewBalance = btcInAccount - exchange.getBtcChange();
        }

        balance.setUsdBalance(usdNewBalance);
        balance.setBtcBalance(btcNewBalance);
        exchange.setBtcPrice(btcPrice);
        this.balanceRepository.save(balance);
        Exchange savedExchange = this.exchangeRepository.save(exchange);
        savedExchange.setUsdBalance(usdNewBalance);
        savedExchange.setBtcBalance(btcNewBalance);
        return savedExchange;
        
    }

}
