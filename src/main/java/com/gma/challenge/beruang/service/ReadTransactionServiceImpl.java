package com.gma.challenge.beruang.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gma.challenge.beruang.model.Transaction;
import com.gma.challenge.beruang.repo.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadTransactionServiceImpl implements ReadTransactionService {

  @Autowired
  TransactionRepository transactionRepo;

  @Override
  public List<Transaction> findByWalletId(Long walletId) {

    // return transactionRepo.findByWalletId(walletId, Sort.by(Sort.Direction.ASC, "date"));
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transaction> findByBudgetId(Long budgetId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transaction> findByCategoryIds(List<Long> categoryIds) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transaction> findByCategoryIdsAndDateRange(List<Long> categoryIds, Date fromDate, Date toDate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transaction> findByAmountRange(BigDecimal fromAmount, BigDecimal toAmount) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transaction> findByAmountRangeAndDateRange(BigDecimal fromAmount, BigDecimal toAmount, Date fromDate,
      Date toDate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transaction> findByWalletIdAndDateRange(Long walletId, Date fromDate, Date toDate) {
    // TODO Auto-generated method stub
    return null;
  }
}
