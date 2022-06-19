package com.gma.challenge.beruang.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.gma.challenge.beruang.data.TransactionResponseData;
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
public class TransactionWriteServiceImplTest implements WriteServiceTest {

  private static final Long VALID_TRANSACTION_ID = Long.valueOf(1);
  private static final Long INVALID_TRANSACTION_ID = Long.valueOf(-1);
  private static final Long VALID_WALLET_ID = Long.valueOf(1);
  private static final Long INVALID_WALLET_ID = Long.valueOf(-1);

  @Autowired
  private TransactionWriteServiceImpl SUT;

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private WalletRepository walletRepository;

  @Test
  @Override
  public void testCreate_validRequestData() {
    TransactionResponseData responseData = SUT.createTransaction(VALID_WALLET_ID,
        TransactionHelper.getValidNewTransactionRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getTransaction());
    assertTrue(TransactionHelper.isTransactionResponseDataEqualsToSample(responseData));
  }

  @Test
  @Override
  public void testCreate_invalidIncompleteRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.createTransaction(VALID_WALLET_ID,
        TransactionHelper.getInvalidIncompleteTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidEmptyRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.createTransaction(VALID_WALLET_ID,
        TransactionHelper.getInvalidEmptyTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testCreate_invalidNullRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.createTransaction(VALID_WALLET_ID,
        TransactionHelper.getInvalidNullTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_validRequestData() {
    assertThrows(WalletNotFoundException.class, () -> SUT.createTransaction(INVALID_WALLET_ID,
        TransactionHelper.getValidNewTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidIncompleteRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.createTransaction(INVALID_WALLET_ID,
        TransactionHelper.getInvalidIncompleteTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidEmptyRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.createTransaction(INVALID_WALLET_ID,
        TransactionHelper.getInvalidEmptyTransactionRequestDataSample()));
  }

  @Test
  public void testCreate_invalidWalletId_invalidNullRequestData() {
    assertThrows(InvalidRequestException.class, () -> SUT.createTransaction(INVALID_WALLET_ID,
        TransactionHelper.getInvalidNullTransactionRequestDataSample()));
  }

  @Test
  @Override
  public void testUpdate_validId_validFullRequestData() {
    TransactionResponseData responseData = SUT.updateTransaction(VALID_WALLET_ID, VALID_TRANSACTION_ID,
        TransactionHelper.getValidFullUpdateTransactionRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getTransaction());
    assertTrue(TransactionHelper.isUpdateTransactionResponseDataEqualsToSample(responseData));
  }

  @Test
  @Override
  public void testUpdate_validId_validPartialRequestData() {
    TransactionResponseData newTransactionData = SUT.createTransaction(VALID_WALLET_ID,
        TransactionHelper.getValidNewTransactionRequestDataSample());

    TransactionResponseData responseData = SUT.updateTransaction(VALID_WALLET_ID,
        newTransactionData.getTransaction().getId(),
        TransactionHelper.getValidPartialUpdateTransactionRequestDataSample());
    assertNotNull(responseData);
    assertNotNull(responseData.getTransaction());
    assertTrue(TransactionHelper.isUpdateTransactionResponseDataEqualsToPartialSample(responseData));
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
  public void testDelete_validId() {
    SUT.deleteTransaction(VALID_WALLET_ID, VALID_TRANSACTION_ID);
    assertNull(transactionRepository.findById(VALID_TRANSACTION_ID).orElse(null));
    assertNotNull(walletRepository.findById(VALID_WALLET_ID).orElse(null));
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
}
