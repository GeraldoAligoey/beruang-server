package com.gma.challenge.beruang.repo;

import java.util.List;

import com.gma.challenge.beruang.model.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BudgetRepository extends JpaRepository<Budget, Long> {

  public List<Budget> findAllByWalletId(Long walletId);

  public List<Budget> findAllByPeriod(String period);
  
}
