package com.gma.challenge.beruang.common.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gma.challenge.beruang.data.NewTransactionRequestData;
import com.gma.challenge.beruang.data.TransactionData;
import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.UpdateTransactionRequestData;

public class TransactionHelper {

  private static final Long TRANSACTION_CATEGORY_ID = Long.valueOf(1);
  private static final Long TRANSACTION_WALLET_ID = Long.valueOf(1);
  private static final LocalDate TRANSACTION_DATE = LocalDate.now();
  private static final BigDecimal TRANSACTION_AMOUNT = new BigDecimal(25);
  private static final String TRANSACTION_NOTE = "Transaction's note";

  private static final Long UPDATE_TRANSACTION_CATEGORY_ID = Long.valueOf(2);
  private static final LocalDate UPDATE_TRANSACTION_DATE = LocalDate.now().minusDays(2);
  private static final BigDecimal UPDATE_TRANSACTION_AMOUNT = new BigDecimal(29.5);
  private static final String UPDATE_TRANSACTION_NOTE = "Update transaction's note";

  public static NewTransactionRequestData getValidNewTransactionRequestDataSample() {
    return NewTransactionRequestData.builder()
        .note(TRANSACTION_NOTE)
        .amount(TRANSACTION_AMOUNT)
        .date(TRANSACTION_DATE)
        .categoryId(TRANSACTION_CATEGORY_ID)
        .build();
  }

  public static boolean isTransactionResponseDataEqualsToSample(TransactionResponseData responseData) {
    TransactionData transactionData = responseData.getTransaction();

    if (!transactionData.getNote().equals(TRANSACTION_NOTE)) {
      return false;
    }

    if (!transactionData.getAmount().equals(TRANSACTION_AMOUNT)) {
      return false;
    }

    if (!transactionData.getDate().equals(TRANSACTION_DATE)) {
      return false;
    }

    if (!transactionData.getCategory().getId().equals(TRANSACTION_CATEGORY_ID)) {
      return false;
    }

    if (!transactionData.getWallet().getId().equals(TRANSACTION_WALLET_ID)) {
      return false;
    }

    return true;
  }

  public static NewTransactionRequestData getInvalidIncompleteTransactionRequestDataSample() {
    return NewTransactionRequestData.builder()
        .amount(TRANSACTION_AMOUNT)
        .categoryId(TRANSACTION_CATEGORY_ID)
        .build();
  }

  public static NewTransactionRequestData getInvalidEmptyTransactionRequestDataSample() {
    return NewTransactionRequestData.builder()
        .build();
  }

  public static NewTransactionRequestData getInvalidNullTransactionRequestDataSample() {
    return null;
  }

  public static UpdateTransactionRequestData getValidFullUpdateTransactionRequestDataSample() {
    return UpdateTransactionRequestData.builder()
        .note(UPDATE_TRANSACTION_NOTE)
        .amount(UPDATE_TRANSACTION_AMOUNT)
        .date(UPDATE_TRANSACTION_DATE)
        .categoryId(UPDATE_TRANSACTION_CATEGORY_ID)
        .build();
  }

  public static boolean isUpdateTransactionResponseDataEqualsToSample(TransactionResponseData responseData) {
    TransactionData transactionData = responseData.getTransaction();

    if (!transactionData.getNote().equals(UPDATE_TRANSACTION_NOTE)) {
      return false;
    }

    if (!transactionData.getAmount().equals(UPDATE_TRANSACTION_AMOUNT)) {
      return false;
    }

    if (!transactionData.getDate().equals(UPDATE_TRANSACTION_DATE)) {
      return false;
    }

    if (!transactionData.getCategory().getId().equals(UPDATE_TRANSACTION_CATEGORY_ID)) {
      return false;
    }

    if (!transactionData.getWallet().getId().equals(TRANSACTION_WALLET_ID)) {
      return false;
    }

    return true;
  }

  public static UpdateTransactionRequestData getValidPartialUpdateTransactionRequestDataSample() {
    return UpdateTransactionRequestData.builder()
        .amount(UPDATE_TRANSACTION_AMOUNT)
        .build();
  }

  public static boolean isUpdateTransactionResponseDataEqualsToPartialSample(
      TransactionResponseData responseData) {
    TransactionData transactionData = responseData.getTransaction();

    if (!transactionData.getNote().equals(TRANSACTION_NOTE)) {
      return false;
    }

    if (!transactionData.getDate().equals(TRANSACTION_DATE)) {
      return false;
    }

    if (!transactionData.getCategory().getId().equals(TRANSACTION_CATEGORY_ID)) {
      return false;
    }

    if (!transactionData.getWallet().getId().equals(TRANSACTION_WALLET_ID)) {
      return false;
    }


    if (!transactionData.getAmount().equals(UPDATE_TRANSACTION_AMOUNT)) {
      return false;
    }

    return true;
  }

  public static UpdateTransactionRequestData getInvalidEmptyUpdateTransactionRequestDataSample() {
    return UpdateTransactionRequestData.builder().build();
  }

  public static UpdateTransactionRequestData getInvalidNullUpdateTransactionRequestDataSample() {
    return null;
  }

}
