package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.data.NewTransactionRequestData;
import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.UpdateTransactionRequestData;

public interface TransactionWriteService {
  
  public TransactionResponseData createTransaction(Long walletId, NewTransactionRequestData newTransactionRequestData);

  public TransactionResponseData updateTransaction(Long walletId, Long transactionId, UpdateTransactionRequestData updateTransactionRequestData);

  public void deleteTransaction(Long walletId, Long transactionId);
  
}
