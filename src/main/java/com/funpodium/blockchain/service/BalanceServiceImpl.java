package com.funpodium.blockchain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funpodium.blockchain.model.Balance;
import com.funpodium.blockchain.repository.IBalanceRepository;

@Service
public class BalanceServiceImpl implements IBalanceService{

    private final IBalanceRepository balanceRepository;

    
    @Autowired
    public BalanceServiceImpl(IBalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }


    @Override
    public Balance createBalance(Balance balance) {
        return this.balanceRepository.save(balance);
    }

}
