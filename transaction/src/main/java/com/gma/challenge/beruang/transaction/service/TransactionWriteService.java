package com.gma.challenge.beruang.transaction.service;

import com.gma.challenge.beruang.generated.dto.NewTransactionRequestData;
import com.gma.challenge.beruang.generated.dto.TransactionResponseData;
import com.gma.challenge.beruang.generated.dto.UpdateTransactionRequestData;

public interface TransactionWriteService {

    public TransactionResponseData createTransaction(
            Long walletId, NewTransactionRequestData newTransactionRequestData);

    public TransactionResponseData updateTransaction(
            Long walletId,
            Long transactionId,
            UpdateTransactionRequestData updateTransactionRequestData);

    public void deleteTransaction(Long walletId, Long transactionId);
}
