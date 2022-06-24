package com.gma.challenge.beruang.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.gma.challenge.beruang.data.TransactionData;
import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.TransactionsResponseData;
import com.gma.challenge.beruang.exception.TransactionNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class TransactionReadServiceImplTest implements ReadServiceTest {

  private static final List<Long> CATEGORY_IDS = Arrays.asList(Long.valueOf(1), Long.valueOf(2));
  private static final BigDecimal FROM_AMOUNT = BigDecimal.ZERO;
  private static final LocalDate TO_DATE = LocalDate.now().plusDays(1);
  private static final LocalDate FROM_DATE = LocalDate.now().minusDays(1);
  private static final BigDecimal TO_AMOUNT = new BigDecimal(500);

  private static final Long VALID_WALLET_ID = Long.valueOf(1);
  private static final Long VALID_TRANSACTION_ID = Long.valueOf(1);
  private static final Long VALID_NORECORD_TRANSACTION_ID = Long.valueOf(1000);
  private static final Long VALID_NORECORD_WALLET_ID = Long.valueOf(10000);
  private static final Long INVALID_WALLET_ID = Long.valueOf(-1);
  private static final Long INVALID_TRANSACTION_ID = Long.valueOf(-1);

  @Autowired
  private TransactionReadServiceImpl SUT;

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  @Override
  public void testFind_empty() {
    TransactionsResponseData responseData = SUT.findTransactions(VALID_WALLET_ID);
    List<TransactionData> transactions = responseData.getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.isEmpty());

    transactions = SUT.findTransactions(VALID_WALLET_ID, null, null, null, null, null).getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.isEmpty());
  }

  @Test
  @Sql("classpath:sql/testFindTransactions_single.sql")
  @Override
  public void testFind_single() {
    TransactionsResponseData responseData = SUT.findTransactions(VALID_WALLET_ID);
    List<TransactionData> transactions = responseData.getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.size() == 1);

    transactions = SUT.findTransactions(VALID_WALLET_ID, null, null, null, null, null).getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.size() == 1);
  }

  @Test
  @Override
  public void testFind_multiple() {
    TransactionsResponseData responseData = SUT.findTransactions(VALID_WALLET_ID);
    List<TransactionData> transactions = responseData.getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.size() > 1);

    transactions = SUT.findTransactions(VALID_WALLET_ID, null, null, null, null, null).getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.size() > 1);
  }

  @Test
  @Override
  public void testFind_validId_recordExist() {
    TransactionResponseData responseData = SUT.findTransaction(VALID_WALLET_ID, VALID_TRANSACTION_ID);
    assertNotNull(responseData.getTransaction());
  }

  @Test
  @Override
  public void testFind_validId_recordNotExist() {
    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(VALID_WALLET_ID, VALID_NORECORD_TRANSACTION_ID));

    assertThrows(WalletNotFoundException.class,
        () -> SUT.findTransaction(VALID_NORECORD_WALLET_ID, VALID_TRANSACTION_ID));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(VALID_NORECORD_WALLET_ID, VALID_NORECORD_TRANSACTION_ID));
  }

  @Test
  @Override
  public void testFind_invalidId() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.findTransaction(INVALID_WALLET_ID, VALID_TRANSACTION_ID));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(VALID_WALLET_ID, INVALID_TRANSACTION_ID));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(INVALID_WALLET_ID, INVALID_TRANSACTION_ID));
  }

  @Test
  @Sql({ "classpath:sql/testFind_empty.sql", "classpath:sql/testFindTransactions_parameters.sql" })
  public void testFind_fullValidParameters() {
    List<TransactionData> transactions = SUT.findTransactions(VALID_WALLET_ID,
        FROM_DATE, TO_DATE,
        FROM_AMOUNT, TO_AMOUNT,
        CATEGORY_IDS).getTransactions();

    assertTrue(transactions.size() == 10);

    for (TransactionData transaction : transactions) {
      assertTrue(transaction.getWallet().getId() == VALID_WALLET_ID);
      assertTrue(transaction.getDate().isBefore(TO_DATE));
      assertTrue(transaction.getDate().isAfter(FROM_DATE));
      assertTrue(transaction.getAmount().compareTo(FROM_AMOUNT) >= 0);
      assertTrue(transaction.getAmount().compareTo(TO_AMOUNT) <= 0);
      assertTrue(CATEGORY_IDS.contains(transaction.getCategory().getId()));
    }
  }

  @Test
  @Sql({ "classpath:sql/testFind_empty.sql", "classpath:sql/testFindTransactions_parameters.sql" })
  public void testFind_partialValidParameters() {
    List<TransactionData> transactions = SUT.findTransactions(VALID_WALLET_ID,
        TO_DATE, null,
        null, null,
        null).getTransactions();

    assertTrue(transactions.isEmpty());

    transactions = SUT.findTransactions(VALID_WALLET_ID,
        null, FROM_DATE,
        null, null,
        null).getTransactions();

    assertTrue(transactions.isEmpty());

    transactions = SUT.findTransactions(VALID_WALLET_ID,
        null, null,
        TO_AMOUNT, null,
        null).getTransactions();

    assertTrue(transactions.size() == 30);

    for (TransactionData transaction : transactions) {
      assertTrue(transaction.getAmount().compareTo(TO_AMOUNT) >= 0);
    }

    transactions = SUT.findTransactions(VALID_WALLET_ID,
        null, null,
        null, new BigDecimal(100),
        null).getTransactions();

    assertTrue(transactions.size() == 5);

    for (TransactionData transaction : transactions) {
      assertTrue(transaction.getAmount().compareTo(new BigDecimal(100)) == 0);
    }

    transactions = SUT.findTransactions(VALID_WALLET_ID,
        null, null,
        null, null,
        Arrays.asList(Long.valueOf(1))).getTransactions();

    assertTrue(transactions.size() == 10);

    for (TransactionData transaction : transactions) {
      assertTrue(Arrays.asList(Long.valueOf(1)).contains(transaction.getCategory().getId()));
    }
  }

  @Test
  @Sql({ "classpath:sql/testFind_empty.sql", "classpath:sql/testFindTransactions_parameters.sql" })
  public void testFind_emptyParameters() {
    List<TransactionData> transactions = SUT.findTransactions(VALID_WALLET_ID,
        null, null,
        null, null,
        null).getTransactions();

    assertTrue(transactions.size() == 50);
  }
}
