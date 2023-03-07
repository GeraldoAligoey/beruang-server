package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.generated.dto.TransactionResponseData;
import com.gma.challenge.beruang.generated.dto.TransactionsResponseData;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionReadService {

    public TransactionResponseData findTransaction(Long walletId, Long transactionId);

    public TransactionsResponseData findTransactions(Long walletId);

    public TransactionsResponseData findTransactions(
            Long walletId,
            LocalDate fromDate,
            LocalDate toDate,
            BigDecimal fromAmount,
            BigDecimal toAmount,
            List<Long> categoryIds);
}
