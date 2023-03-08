package com.gma.challenge.beruang.budget.repo;

import com.gma.challenge.beruang.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    public List<Budget> findByWalletId(Long walletId);

    public List<Budget> findByPeriod(String period);
}
