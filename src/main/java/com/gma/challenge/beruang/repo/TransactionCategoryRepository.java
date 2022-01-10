package com.gma.challenge.beruang.repo;

import com.gma.challenge.beruang.model.TransactionCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long> {
  
}
