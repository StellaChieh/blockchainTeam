package com.funpodium.blockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.funpodium.blockchain.model.Exchange;

public interface IExchangeRepository extends JpaRepository<com.funpodium.blockchain.model.Exchange, Integer>{
    
}
