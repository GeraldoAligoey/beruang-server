package com.gma.challenge.beruang.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  }

  @Test
  @Sql("classpath:sql/testFindTransactions_single.sql")
  @Override
  public void testFind_single() {
    TransactionsResponseData responseData = SUT.findTransactions(VALID_WALLET_ID);
    List<TransactionData> transactions = responseData.getTransactions();

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

}
