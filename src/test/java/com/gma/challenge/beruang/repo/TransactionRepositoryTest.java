package com.gma.challenge.beruang.repo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.gma.challenge.beruang.model.Transaction;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql("classpath:test_transaction.sql")
public class TransactionRepositoryTest {

  private static final Logger LOG = LoggerFactory.getLogger(TransactionRepositoryTest.class);

  @Autowired
  private TransactionRepository transactionRepository;

  @Test
  public void findAllByWalletId() {
    List<Transaction> transactions = transactionRepository.findByWalletId(1l, Sort.by(Sort.Direction.DESC, "date"));

    for (Transaction transaction : transactions) {
      LOG.info("Transaction id: {}; note: {}; date: {}; wallet id: {}", transaction.getId(), transaction.getNote(),
          transaction.getDate(), transaction.getWallet().getId());
    }

    assertTrue(transactions.size() == 4);
  }

  @Test
  public void findAllByWalletIdAndDateRange() {
    LocalDate fromDate = LocalDate.of(2022, 3, 29);
    LocalDate toDate = LocalDate.of(2022, 4, 21);

    List<Transaction> transactions = transactionRepository.findByWalletIdAndDateRange(1l, fromDate, toDate,
        Sort.by(Sort.Direction.DESC, "date"));

    for (Transaction transaction : transactions) {
      LOG.info("Transaction id: {}; note: {}; date: {}; wallet id: {}", transaction.getId(), transaction.getNote(),
          transaction.getDate(), transaction.getWallet().getId());
    }

    assertTrue(transactions.size() == 3);
  }

  @Test
  public void findAllByBudgetId() {
    List<Transaction> transactions = transactionRepository.findByBudgetId(1l);

    for (Transaction transaction : transactions) {
      LOG.info("Transaction id: {}; note: {}; date: {}; wallet id: {}; category id: {}", transaction.getId(),
          transaction.getNote(), transaction.getDate(), transaction.getWallet().getId(),
          transaction.getCategory().getId());
    }

    assertTrue(transactions.size() == 3);
  }

  @Test
  public void findAllByCategoryIds() {
    List<Transaction> transactions = transactionRepository.findByCategoryIds(List.of(2l, 3l, 1l),
        Sort.by(Sort.Direction.DESC, "date"));

    for (Transaction transaction : transactions) {
      LOG.info("Transaction id: {}; note: {}; date: {}; wallet id: {}; category id: {}", transaction.getId(),
          transaction.getNote(), transaction.getDate(), transaction.getWallet().getId(),
          transaction.getCategory().getId());
    }

    assertTrue(transactions.size() == 5);
  }

  @Test
  public void findAllByCategoryIdsAndDateRange() {
    LocalDate fromDate = LocalDate.of(2022, 3, 29);
    LocalDate toDate = LocalDate.of(2022, 4, 22);

    List<Transaction> transactions = transactionRepository.findByCategoryIdsAndDateRange(List.of(3l), fromDate, toDate,
        Sort.by(Sort.Direction.DESC, "date"));

    for (Transaction transaction : transactions) {
      LOG.info("Transaction id: {}; note: {}; date: {}; wallet id: {}; category id: {}", transaction.getId(),
          transaction.getNote(), transaction.getDate(), transaction.getWallet().getId(),
          transaction.getCategory().getId());
    }

    assertTrue(transactions.size() == 3);
  }

  @Test
  public void findByAmountRange() {
    BigDecimal fromAmount = new BigDecimal(10);
    BigDecimal toAmount = new BigDecimal(20);

    List<Transaction> transactions = transactionRepository.findByAmountRange(fromAmount, toAmount,
        Sort.by(Sort.Direction.DESC, "date"));

    for (Transaction transaction : transactions) {
      LOG.info("Transaction id: {}; note: {}; date: {}; wallet id: {}; category id: {}", transaction.getId(),
          transaction.getNote(), transaction.getDate(), transaction.getWallet().getId(),
          transaction.getCategory().getId());
    }

    assertTrue(transactions.size() == 3);
  }

  @Test
  public void findAllByAmountRangeAndDateRange() {
    BigDecimal fromAmount = new BigDecimal(10);
    BigDecimal toAmount = new BigDecimal(20);
    LocalDate fromDate = LocalDate.of(2022, 4, 1);
    LocalDate toDate = LocalDate.of(2022, 4, 30);

    List<Transaction> transactions = transactionRepository.findByAmountRangeAndDateRange(fromAmount, toAmount, fromDate,
        toDate, Sort.by(Sort.Direction.DESC, "date"));

    for (Transaction transaction : transactions) {
      LOG.info("Transaction id: {}; note: {}; date: {}; wallet id: {}; category id: {}", transaction.getId(),
          transaction.getNote(), transaction.getDate(), transaction.getWallet().getId(),
          transaction.getCategory().getId());
    }

    assertTrue(transactions.size() == 2);
  }
}
