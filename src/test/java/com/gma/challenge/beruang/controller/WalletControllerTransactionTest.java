package com.gma.challenge.beruang.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.gma.challenge.beruang.data.TransactionData;
import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.TransactionsResponseData;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.exception.InvalidRequestException;
import com.gma.challenge.beruang.exception.TransactionNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.helper.TransactionHelper;
import com.gma.challenge.beruang.repo.TransactionRepository;
import com.gma.challenge.beruang.repo.WalletRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class WalletControllerTransactionTest implements ControllerTest {

  private static final Long VALID_NORECORD_TRANSACTION_ID = Long.valueOf(1000);
  private static final Long VALID_NORECORD_WALLET_ID = Long.valueOf(10000);
  private static final Long VALID_TRANSACTION_ID = Long.valueOf(1);
  private static final Long INVALID_TRANSACTION_ID = Long.valueOf(-1);
  private static final Long INVALID_WALLET_ID = Long.valueOf(-1);
  private static final Long VALID_WALLET_ID = Long.valueOf(1l);

  @Autowired
  private WalletController SUT;

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private WalletRepository walletRepository;

  @Test
  @Override
  public void testCreate_validNewRequestData() {
    ResponseEntity<TransactionResponseData> responseEntity = SUT.createTransaction(VALID_WALLET_ID,
        TransactionHelper.getValidNewTransactionRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getTransaction());
    assertTrue(TransactionHelper.isTransactionResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testCreate_invalidEmptyNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createTransaction(VALID_WALLET_ID, TransactionHelper.getInvalidEmptyTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidIncompleteNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createTransaction(VALID_WALLET_ID,
            TransactionHelper.getInvalidIncompleteTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidNullNewRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createTransaction(VALID_WALLET_ID, TransactionHelper.getInvalidNullTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_validRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.createTransaction(INVALID_WALLET_ID, TransactionHelper.getValidNewTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidIncompleteRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createTransaction(INVALID_WALLET_ID,
            TransactionHelper.getInvalidIncompleteTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createTransaction(INVALID_WALLET_ID,
            TransactionHelper.getInvalidEmptyTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.createTransaction(INVALID_WALLET_ID, TransactionHelper.getInvalidNullTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_validFullRequestData() {
    ResponseEntity<TransactionResponseData> responseEntity = SUT.updateTransaction(VALID_WALLET_ID,
        VALID_TRANSACTION_ID, TransactionHelper.getValidFullUpdateTransactionRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getTransaction());
    assertTrue(TransactionHelper.isUpdateTransactionResponseDataEqualsToSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_validPartialRequestData() {
    ResponseEntity<TransactionResponseData> newResponseEntity = SUT.createTransaction(VALID_WALLET_ID,
        TransactionHelper.getValidNewTransactionRequestDataSample());

    ResponseEntity<TransactionResponseData> responseEntity = SUT.updateTransaction(VALID_WALLET_ID,
        newResponseEntity.getBody().getTransaction().getId(), TransactionHelper.getValidPartialUpdateTransactionRequestDataSample());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody().getTransaction());
    assertTrue(TransactionHelper.isUpdateTransactionResponseDataEqualsToPartialSample(responseEntity.getBody()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateTransaction(VALID_WALLET_ID, VALID_TRANSACTION_ID,
            TransactionHelper.getInvalidEmptyUpdateTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    assertThrows(IncompleteRequestDataException.class,
        () -> SUT.updateTransaction(VALID_WALLET_ID, VALID_TRANSACTION_ID,
            TransactionHelper.getInvalidNullUpdateTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, VALID_TRANSACTION_ID,
            TransactionHelper.getValidFullUpdateTransactionRequestDataSample()));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.updateTransaction(VALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getValidFullUpdateTransactionRequestDataSample()));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getValidFullUpdateTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, VALID_TRANSACTION_ID,
            TransactionHelper.getValidPartialUpdateTransactionRequestDataSample()));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.updateTransaction(VALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getValidPartialUpdateTransactionRequestDataSample()));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getValidPartialUpdateTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    assertThrows(InvalidRequestException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, VALID_TRANSACTION_ID,
            TransactionHelper.getInvalidEmptyUpdateTransactionRequestDataSample()));

    assertThrows(InvalidRequestException.class,
        () -> SUT.updateTransaction(VALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getInvalidEmptyUpdateTransactionRequestDataSample()));

    assertThrows(InvalidRequestException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getInvalidEmptyUpdateTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    assertThrows(InvalidRequestException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, VALID_TRANSACTION_ID,
            TransactionHelper.getInvalidNullUpdateTransactionRequestDataSample()));

    assertThrows(InvalidRequestException.class,
        () -> SUT.updateTransaction(VALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getInvalidNullUpdateTransactionRequestDataSample()));

    assertThrows(InvalidRequestException.class,
        () -> SUT.updateTransaction(INVALID_WALLET_ID, INVALID_TRANSACTION_ID,
            TransactionHelper.getInvalidNullUpdateTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testDelete_validId_linked() {
    SUT.deleteTransaction(VALID_WALLET_ID, VALID_TRANSACTION_ID);
    assertNull(transactionRepository.findById(VALID_TRANSACTION_ID).orElse(null));
    assertNotNull(walletRepository.findById(VALID_WALLET_ID).orElse(null));
  }

  @Override
  public void testDelete_validId_unlinked() {
    // Transaction cannot be unlinked from wallet and category
  }

  @Test
  @Override
  public void testDelete_invalidId() {
    assertThrows(TransactionNotFoundException.class,
        () -> SUT.deleteTransaction(INVALID_WALLET_ID, INVALID_TRANSACTION_ID));

    assertThrows(WalletNotFoundException.class,
        () -> SUT.deleteTransaction(INVALID_WALLET_ID, VALID_TRANSACTION_ID));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.deleteTransaction(VALID_WALLET_ID, INVALID_TRANSACTION_ID));
  }

  @Test
  @Override
  public void testFindRecord_validId_recordExist() {
    ResponseEntity<TransactionResponseData> responseEntity = SUT.findTransaction(VALID_WALLET_ID, VALID_TRANSACTION_ID);
    assertNotNull(responseEntity.getBody().getTransaction());
  }

  @Test
  @Override
  public void testFindRecord_validId_recordNotExist() {
    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(VALID_WALLET_ID, VALID_NORECORD_TRANSACTION_ID));

    assertThrows(WalletNotFoundException.class,
        () -> SUT.findTransaction(VALID_NORECORD_WALLET_ID, VALID_TRANSACTION_ID));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(VALID_NORECORD_WALLET_ID, VALID_NORECORD_TRANSACTION_ID));
  }

  @Test
  @Override
  public void testFindRecord_invalidId() {
    assertThrows(WalletNotFoundException.class,
        () -> SUT.findTransaction(INVALID_WALLET_ID, VALID_TRANSACTION_ID));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(VALID_WALLET_ID, INVALID_TRANSACTION_ID));

    assertThrows(TransactionNotFoundException.class,
        () -> SUT.findTransaction(INVALID_WALLET_ID, INVALID_TRANSACTION_ID));
  }

  @Test
  @Sql("classpath:sql/testFind_empty.sql")
  @Override
  public void testFindRecords_empty() {
    ResponseEntity<TransactionsResponseData> responseEntity = SUT.findTransactions(VALID_WALLET_ID);
    List<TransactionData> transactions = responseEntity.getBody().getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.isEmpty());
  }

  @Test
  @Sql("classpath:sql/testFindTransactions_single.sql")
  @Override
  public void testFindRecords_single() {
    ResponseEntity<TransactionsResponseData> responseEntity = SUT.findTransactions(VALID_WALLET_ID);
    List<TransactionData> transactions = responseEntity.getBody().getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.size() == 1);
  }

  @Test
  @Override
  public void testFindRecords_multiple() {
    ResponseEntity<TransactionsResponseData> responseEntity = SUT.findTransactions(VALID_WALLET_ID);
    List<TransactionData> transactions = responseEntity.getBody().getTransactions();

    assertNotNull(transactions);
    assertTrue(transactions.size() > 1);

  }

}
