package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.gma.challenge.beruang.model.Wallet;
import com.gma.challenge.beruang.util.WalletDataSample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@Slf4j
public class WalletRepositoryTest {

  @Autowired
  WalletRepository walletRepository;

  @BeforeAll
  public void init() {
    walletRepository.saveAll(new WalletDataSample().getSamples());
  }

  @Test
  public void testFindById() {
    log.info("WalletRepositoryTest - testFindById");

    Wallet wallet1 = walletRepository.findById(1l).get();
    assertEquals("Test Wallet 001", wallet1.getName());
    assertEquals("MYR", wallet1.getDefaultCurrencyCode());

    Wallet wallet2 = walletRepository.findById(2l).get();
    assertEquals("Test Wallet 002", wallet2.getName());
    assertEquals("USD", wallet2.getDefaultCurrencyCode());
  }
  
  @Test
  public void testFindAll() {
    log.info("WalletRepositoryTest - testFindAll");

    List<Wallet> wallets = walletRepository.findAll();
    assertEquals(2, wallets.size());
  }
}
