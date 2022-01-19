package com.gma.challenge.beruang.repo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gma.challenge.beruang.model.Transaction;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Query("select t from Transaction t where t.wallet.id = ?1")
  List<Transaction> findByWalletId(Long walletId, Sort sort);
  
  @Query("select t from Transaction t where t.wallet.id = ?1 and t.date between ?2 and ?3")
  List<Transaction> findByWalletIdAndDateRange(Long walletId, Date fromDate, Date toDate, Sort sort);

  @Query("select t from Transaction t where t.budget.id = ?1")
  List<Transaction> findByBudgetId(Long budgetId);

  @Query("select t from Transaction t where t.categories.id in ?1")
  List<Transaction> findByCategoryIds(List<Long> categoryIds, Sort sort);

  @Query("select t from Transaction t where t.categories.id in ?1 and t.date between ?2 and ?3")
  List<Transaction> findByCategoryIdsAndDateRange(List<Long> categoryIds, Date fromDate, Date toDate, Sort sort);

  @Query("select t from Transaction t where t.amount between ?1 and ?2")
  List<Transaction> findByAmountRange(BigDecimal fromAmount, BigDecimal toAmount, Sort sort);

  @Query("select t from Transaction t where t.amount between ?1 and ?2 and t.date between ?3 and ?4")
  List<Transaction> findByAmountRangeAndDateRange(BigDecimal fromAmount, BigDecimal toAmount, Date fromDate, Date toDate, Sort sort);
}
