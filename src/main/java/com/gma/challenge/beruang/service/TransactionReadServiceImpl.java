package com.gma.challenge.beruang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.TransactionsResponseData;
import com.gma.challenge.beruang.domain.Transaction;
import com.gma.challenge.beruang.exception.TransactionNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.TransactionRepository;
import com.gma.challenge.beruang.util.Mapper;

@Transactional
@Service
public class TransactionReadServiceImpl implements TransactionReadService {

  private final TransactionRepository transactionRepository;

  @Autowired
  public TransactionReadServiceImpl(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public TransactionResponseData findTransaction(Long walletId, Long transactionId) {
    Transaction transaction = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new TransactionNotFoundException("Invalid transaction id"));

    if (!transaction.getWallet().getId().equals(walletId)) {
      throw new WalletNotFoundException("Invalid wallet id");
    }

    return TransactionResponseData.builder().transaction(Mapper.toTransactionData(transaction)).build();
  }

  @Override
  public TransactionsResponseData findTransactions(Long walletId) {
    List<Transaction> transactions = transactionRepository.findByWalletId(walletId,
        Sort.by(Sort.Direction.DESC, "date"));

    if (!isWalletIdValid(walletId) && transactions == null) {
      throw new WalletNotFoundException("Invalid wallet id");
    }

    return TransactionsResponseData.builder()
        .transactions(
            transactions.stream().map(transaction -> Mapper.toTransactionData(transaction))
                .collect(Collectors.toList()))
        .build();
  }

  private boolean isWalletIdValid(Long walletId) {
    return walletId < 0;
  }

}
