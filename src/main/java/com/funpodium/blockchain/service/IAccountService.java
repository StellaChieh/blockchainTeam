package com.funpodium.blockchain.service;

import com.funpodium.blockchain.model.Account;

public interface IAccountService {

    public Account createAccount(Account account);

    public void deleteAccount(int userId);
}
