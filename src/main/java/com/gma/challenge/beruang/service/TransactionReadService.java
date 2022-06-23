package com.gma.challenge.beruang.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.TransactionsResponseData;

public interface TransactionReadService {

  public TransactionResponseData findTransaction(Long walletId, Long transactionId);

  public TransactionsResponseData findTransactions(Long walletId);

  public TransactionsResponseData findTransactions(Long walletId, LocalDate fromDate, LocalDate toDate,
      BigDecimal fromAmount, BigDecimal toAmount, List<Long> categoryIds);
}
