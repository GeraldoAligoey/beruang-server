package com.gma.challenge.beruang.category.repo;

import com.gma.challenge.beruang.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(
            value =
                    "select * from category where category.id in (select w_c.categories_id from"
                            + " wallet_categories w_c where w_c.wallet_id = :walletId) order by"
                            + " category.name",
            nativeQuery = true)
    public Set<Category> findByWalletId(Long walletId);

    @Query(
            value =
                    "select * from category where category.id in (select b_c.categories_id from"
                            + " budget_categories b_c where b_c.budget_id = :budgetId) order by"
                            + " category.name",
            nativeQuery = true)
    public Set<Category> findByBudgetId(Long budgetId);

    @Query(
            value =
                    "select * from category where category.id in (select distinct category.id from"
                            + " category join transaction on transaction.category_id = category.id"
                            + " where transaction.wallet_id = :walletId)",
            nativeQuery = true)
    public Set<Category> findUsedCategoriesByWallet(Long walletId);
}
