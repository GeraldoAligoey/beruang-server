package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.TransactionsResponseData;

public interface TransactionReadService {
  
  public TransactionResponseData findTransaction(Long walletId, Long transactionId);

  public TransactionsResponseData findTransactions(Long walletId);
  
}
