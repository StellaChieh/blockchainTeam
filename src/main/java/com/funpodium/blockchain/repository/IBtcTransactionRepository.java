package com.funpodium.blockchain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funpodium.blockchain.model.BTCTransaction;

public interface IBtcTransactionRepository extends JpaRepository<BTCTransaction, Integer>{
    
    public List<BTCTransaction> findByUserId(int userId);
}
