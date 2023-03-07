package com.gma.challenge.beruang.repo;

import com.gma.challenge.beruang.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByWalletId(Long walletId, Sort sort);

    @Query("select t from Transaction t where t.wallet.id = ?1 and t.date between ?2 and ?3")
    List<Transaction> findByWalletIdAndDateRange(
            Long walletId, LocalDate fromDate, LocalDate toDate, Sort sort);

    @Query(
            value =
                    "select * from (select * from transaction t where t.wallet_id = (select"
                            + " b.wallet_id from budget b where b.id = :budgetId)) x where"
                            + " x.category_id in (select bc.categories_id from budget_categories bc"
                            + " where bc.budget_id = :budgetId) order by x.date desc",
            nativeQuery = true)
    List<Transaction> findByBudgetId(Long budgetId);

    @Query("select t from Transaction t where t.category.id in ?1")
    List<Transaction> findByCategoryIds(List<Long> categoryIds, Sort sort);

    @Query("select t from Transaction t where t.category.id in ?1 and t.date between ?2 and ?3")
    List<Transaction> findByCategoryIdsAndDateRange(
            List<Long> categoryIds, LocalDate fromDate, LocalDate toDate, Sort sort);

    @Query("select t from Transaction t where t.amount between ?1 and ?2")
    List<Transaction> findByAmountRange(BigDecimal fromAmount, BigDecimal toAmount, Sort sort);

    @Query(
            "select t from Transaction t where t.amount between ?1 and ?2 and t.date between ?3 and"
                    + " ?4")
    List<Transaction> findByAmountRangeAndDateRange(
            BigDecimal fromAmount,
            BigDecimal toAmount,
            LocalDate fromDate,
            LocalDate toDate,
            Sort sort);

    @Modifying
    @Query("delete from Transaction t where t.wallet.id = ?1 and t.category.id = ?2")
    void deleteByWalletIdAndCategoryId(Long walletId, Long categoryId);

    @Modifying
    @Query(
            "update Transaction t set t.category.id = ?3 where t.category.id = ?2 and t.wallet.id ="
                    + " ?1")
    void moveTransactionToNewCategory(Long walletId, Long oldCategoryId, Long newCategoryId);

    @Modifying
    @Query("update Transaction t set t.wallet.id = ?2 where t.wallet.id = ?1")
    void moveTransactionToNewWallet(Long oldWalletId, Long newWalletId);
}
