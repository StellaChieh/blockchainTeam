package com.funpodium.blockchain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funpodium.blockchain.model.Account;

public interface IAccountRepository extends JpaRepository<com.funpodium.blockchain.model.Account, Integer>{

    public Optional<Account> findByUsername(String username);
}
