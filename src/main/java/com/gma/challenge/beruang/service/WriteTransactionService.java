package com.gma.challenge.beruang.service;

import java.math.BigDecimal;
import java.util.Date;

import com.gma.challenge.beruang.model.Transaction;

public interface WriteTransactionService {

  public Transaction createTransaction(Long walletId, Long categoryId, BigDecimal amount, Date transactionDate);

  public Transaction createTransaction(Long walletId, Long categoryId, BigDecimal amount, Date transactionDate,
      String note);

  public Transaction updateTransactionById(Long id, Transaction transaction);

  public void deleteTransactionById(Long transactionId);

  public void deleteTransactionByCategoryId(Long categoryId);
}
