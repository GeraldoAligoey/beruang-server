package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.exception.TransactionNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.generated.dto.TransactionData;
import com.gma.challenge.beruang.generated.dto.TransactionResponseData;
import com.gma.challenge.beruang.generated.dto.TransactionsResponseData;
import com.gma.challenge.beruang.model.Transaction;
import com.gma.challenge.beruang.repo.TransactionRepository;
import com.gma.challenge.beruang.service.util.Mapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TransactionReadServiceImpl implements TransactionReadService {

    private final TransactionRepository transactionRepository;

    public TransactionReadServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponseData findTransaction(Long walletId, Long transactionId) {
        Transaction transaction =
                transactionRepository
                        .findById(transactionId)
                        .orElseThrow(
                                () -> new TransactionNotFoundException("Invalid transaction id"));

        if (!transaction.getWallet().getId().equals(walletId)) {
            throw new WalletNotFoundException("Invalid wallet id");
        }

        return TransactionResponseData.builder()
                .transaction(Mapper.toTransactionData(transaction))
                .build();
    }

    @Override
    public TransactionsResponseData findTransactions(Long walletId) {
        List<Transaction> transactions =
                transactionRepository.findByWalletId(
                        walletId, Sort.by(Sort.Direction.DESC, "date"));

        if (!isWalletIdValid(walletId) && transactions == null) {
            throw new WalletNotFoundException("Invalid wallet id");
        }

        return TransactionsResponseData.builder()
                .transactions(
                        transactions.stream()
                                .map(transaction -> Mapper.toTransactionData(transaction))
                                .collect(Collectors.toList()))
                .build();
    }

    private boolean isWalletIdValid(Long walletId) {
        return walletId < 0;
    }

    @Override
    public TransactionsResponseData findTransactions(
            Long walletId,
            LocalDate fromDate,
            LocalDate toDate,
            BigDecimal fromAmount,
            BigDecimal toAmount,
            List<Long> categoryIds) {
        TransactionsResponseData responseData = findTransactions(walletId);
        List<TransactionData> transactions = responseData.getTransactions();

        if (fromDate != null) {
            transactions.removeIf(transaction -> transaction.getDate().isBefore(fromDate));
        }

        if (toDate != null) {
            transactions.removeIf(transaction -> transaction.getDate().isAfter(toDate));
        }

        if (fromAmount != null) {
            transactions.removeIf(transaction -> transaction.getAmount().compareTo(fromAmount) < 0);
        }

        if (toAmount != null) {
            transactions.removeIf(transaction -> transaction.getAmount().compareTo(toAmount) > 0);
        }

        if (categoryIds != null && !categoryIds.isEmpty()) {
            transactions.removeIf(
                    transaction -> !categoryIds.contains(transaction.getCategory().getId()));
        }

        return TransactionsResponseData.builder().transactions(transactions).build();
    }
}
