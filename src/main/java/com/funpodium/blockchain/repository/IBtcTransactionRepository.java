package com.funpodium.blockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funpodium.blockchain.model.BTCTransaction;

public interface IBtcTransactionRepository extends JpaRepository<BTCTransaction, Integer>{
    
}
