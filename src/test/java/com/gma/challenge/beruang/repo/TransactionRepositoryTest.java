package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.NoSuchElementException;

import com.gma.challenge.beruang.model.Transaction;
import com.gma.challenge.beruang.util.TransactionDataSample;

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
public class TransactionRepositoryTest {
  
  @Autowired
  TransactionRepository transactionRepository;

  @BeforeAll
  public void init() {
    transactionRepository.saveAll(new TransactionDataSample().getSamples());
  }

  @Test
  @Order(1)
  public void testFindById() {
    log.info("TransactionRepositoryTest - testFindById");

    Transaction transaction1 = transactionRepository.findById(1l).get();
    assertEquals("Rental fee", transaction1.getNote());
    assertEquals("MYR", transaction1.getWallet().getDefaultCurrencyCode());
    assertEquals("Rental", transaction1.getCategory().getName());

    Transaction transaction2 = transactionRepository.findById(2l).get();
    assertEquals("Food and drinks", transaction2.getNote());
    assertEquals("MYR", transaction1.getWallet().getDefaultCurrencyCode());
    assertEquals("Food and Drinks", transaction2.getCategory().getName());
  }
  
  @Test
  @Order(2)
  public void testFindAll() {
    log.info("TransactionRepositoryTest - testFindAll");

    List<Transaction> transactions = transactionRepository.findAll();
    assertEquals(3, transactions.size());
  }

  @Test
  @Order(3)
  public void testDelete() {
    log.info("TransactionRepositoryTest - testDelete");

    transactionRepository.deleteById(1l);
    Transaction transaction = null;

    try {
      transaction = transactionRepository.findById(1l).get();
    } catch(NoSuchElementException e) {
      log.info(e.getMessage());
    }

    assertNull(transaction);
  }

  @Test
  @Order(4)
  public void testFindByWalletIdSortAsc() {
    log.info("TransactionRepositoryTest - testFindByWalletIdSortAsc");

  }

  @Test
  @Order(5)
  public void testFindByWalletIdSortDesc() {
    log.info("TransactionRepositoryTest - testFindByWalletIdSortDesc");

  }
}
