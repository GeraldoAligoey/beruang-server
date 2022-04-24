package com.gma.challenge.beruang.repo;

import java.util.Set;

import com.gma.challenge.beruang.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query(value = "select * from category where category.id in (select w_c.categories_id from wallet_categories w_c where w_c.wallet_id = :walletId) order by category.name", nativeQuery = true)
  public Set<Category> findByWalletId(Long walletId);

  @Query(value = "select * from category where category.id in (select b_c.categories_id from budget_categories b_c where b_c.budget_id = :budgetId) order by category.name", nativeQuery = true)
  public Set<Category> findByBudgetId(Long budgetId);
}
