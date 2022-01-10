package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.NoSuchElementException;

import com.gma.challenge.beruang.model.Wallet;
import com.gma.challenge.beruang.util.WalletDataSample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
  @Order(1)
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
  @Order(2)
  public void testFindAll() {
    log.info("WalletRepositoryTest - testFindAll");

    List<Wallet> wallets = walletRepository.findAll();
    assertEquals(2, wallets.size());
  }

  @Test
  @Order(3)
  public void testDelete() {
    log.info("WalletRepositoryTest - testDelete");

    walletRepository.deleteById(1l);
    Wallet wallet = null;

    try {
      wallet = walletRepository.findById(1l).get();
    } catch(NoSuchElementException e) {
      log.info(e.getMessage());
    }

    assertNull(wallet);
  }
}
