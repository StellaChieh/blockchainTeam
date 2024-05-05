package com.funpodium.blockchain.service;

import java.time.LocalDateTime;

public interface IQuoteMachine {

    public LocalDateTime getInitTime();

    public int quote(LocalDateTime currentTime);

}
