package com.fonpodium.blockchain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonpodium.blockchain.model.Account;

public interface IAccountRepository extends JpaRepository<com.fonpodium.blockchain.model.Account, Integer>{

    public Optional<Account> findByUsername(String username);
}
