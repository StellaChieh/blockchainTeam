package com.funpodium.blockchain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(
  locations = "classpath:application-test.properties")
public class QuoteMachineTest {

    @Autowired
	private IQuoteMachine quoteMachine;
    
    @Test
	void contextLoads() throws Exception{
		assertThat(quoteMachine).isNotNull();
	}

    @Test
    void price_Is_460_After_3_Mins() {
        LocalDateTime initTime = quoteMachine.getInitTime();
        Duration durationToAdd = Duration.ofMinutes(3);
        LocalDateTime curTime = initTime.plus(durationToAdd);
        assertEquals(460, quoteMachine.quote(curTime));
    }

    @Test
    void price_Is_100_After_6_Mins() {
        LocalDateTime initTime = quoteMachine.getInitTime();
        Duration durationToAdd = Duration.ofMinutes(6);
        LocalDateTime curTime = initTime.plus(durationToAdd);
        assertEquals(100, quoteMachine.quote(curTime));
    }

    @Test
    void price_Is_100_After_12_Mins() {
        LocalDateTime initTime = quoteMachine.getInitTime();
        Duration durationToAdd = Duration.ofMinutes(12);
        LocalDateTime curTime = initTime.plus(durationToAdd);
        assertEquals(100, quoteMachine.quote(curTime));
    }

    @Test
    void price_Is_460_After_15_Mins() {
        LocalDateTime initTime = quoteMachine.getInitTime();
        Duration durationToAdd = Duration.ofMinutes(15);
        LocalDateTime curTime = initTime.plus(durationToAdd);
        assertEquals(460, quoteMachine.quote(curTime));
    }

    @Test
    void price_Is_120_After_6_Mins_And_13_Secs() {
        LocalDateTime initTime = quoteMachine.getInitTime();
        Duration durationToAdd = Duration.ofMinutes(6).plusSeconds(13);
        LocalDateTime curTime = initTime.plus(durationToAdd);
        assertEquals(120, quoteMachine.quote(curTime));
    }

    @Test
    void price_Is_220_After_5_Mins() {
        LocalDateTime initTime = quoteMachine.getInitTime();
        Duration durationToAdd = Duration.ofMinutes(5);
        LocalDateTime curTime = initTime.plus(durationToAdd);
        assertEquals(220, quoteMachine.quote(curTime));
    }

}
