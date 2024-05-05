package com.funpodium.blockchain.service;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class QuoteMachineImpl implements IQuoteMachine{

    private LocalDateTime initTime;
    
    @PostConstruct
    private void setInitTime(){
        initTime = LocalDateTime.now();
    }

    public LocalDateTime getInitTime(){
        return initTime;
    }

    public int quote(LocalDateTime currentTime) {
        Duration duration = Duration.between(initTime, currentTime);
        long remainderDuration = (int) (duration.getSeconds() % (6 * 60));
        if ( remainderDuration >= 60 * 3) { // price decreased
            return 460 - (int)Math.floorDiv(remainderDuration - 60*3, 5)*10;
        } else { // price increased
            return 100 + (int)Math.floorDiv(remainderDuration, 5)*10;
        }
    }


}
