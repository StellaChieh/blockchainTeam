package com.funpodium.blockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funpodium.blockchain.model.Balance;

import java.util.Optional;

public interface IBalanceRepository extends JpaRepository<com.funpodium.blockchain.model.Balance, Integer>{

    public Optional<Balance> findByUserId(int userId);
}
