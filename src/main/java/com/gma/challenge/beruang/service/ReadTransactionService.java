package com.gma.challenge.beruang.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gma.challenge.beruang.model.Transaction;

public interface ReadTransactionService {

  public List<Transaction> findByWalletId(Long walletId);

  public List<Transaction> findByWalletIdAndDateRange(Long walletId, Date fromDate, Date toDate);

  public List<Transaction> findByBudgetId(Long budgetId);

  public List<Transaction> findByCategoryIds(List<Long> categoryIds);

  public List<Transaction> findByCategoryIdsAndDateRange(List<Long> categoryIds, Date fromDate, Date toDate);

  public List<Transaction> findByAmountRange(BigDecimal fromAmount, BigDecimal toAmount);

  public List<Transaction> findByAmountRangeAndDateRange(BigDecimal fromAmount, BigDecimal toAmount, Date fromDate,
      Date toDate);
}
