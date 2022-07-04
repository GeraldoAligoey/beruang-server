package com.gma.challenge.beruang.unit.repo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gma.challenge.beruang.common.RepositoryTest;
import com.gma.challenge.beruang.domain.Transaction;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.repo.TransactionRepository;
import com.gma.challenge.beruang.util.Mapper;

@DataJpaTest
public class TransactionRepositoryTest implements RepositoryTest {

  @Autowired
  private TransactionRepository SUT;

  @Test
  @Override
  public void testFindAll() {
    List<Transaction> transactions = SUT.findAll();
    assertNotNull(transactions);
    assertTrue(transactions.size() > 1);
    assertNotNull(transactions.get(0).getId());
    assertNotNull(transactions.get(0).getNote());
    assertNotNull(transactions.get(0).getAmount());
    assertNotNull(transactions.get(0).getCategory());
    assertNotNull(transactions.get(0).getDate());
    assertNotNull(transactions.get(0).getWallet());
  }

  @Test
  @Override
  public void testCreate() {
    Transaction newTransaction = new Transaction();
    newTransaction.setNote("fried rice");
    newTransaction.setAmount(new BigDecimal(10.50));
    newTransaction.setCategory(Mapper.toCategory(Long.valueOf(3)));
    newTransaction.setDate(LocalDate.now());

    Wallet wallet = new Wallet();
    wallet.setId(Long.valueOf(1));

    newTransaction.setWallet(wallet);
    Transaction savedTransaction = SUT.save(newTransaction);

    assertNotNull(savedTransaction);
    assertNotNull(savedTransaction.getId());
  }

  @Test
  @Override
  public void testUpdate() {
    Transaction newTransaction = new Transaction();
    newTransaction.setNote("fried rice");
    newTransaction.setAmount(new BigDecimal(10.50));
    newTransaction.setCategory(Mapper.toCategory(Long.valueOf(3)));
    newTransaction.setDate(LocalDate.now());

    Wallet wallet = new Wallet();
    wallet.setId(Long.valueOf(1));

    newTransaction.setWallet(wallet);
    Transaction savedTransaction = SUT.save(newTransaction);

    savedTransaction.setNote("chicken rice");

    Transaction updatedTransaction = SUT.save(savedTransaction);

    assertNotNull(updatedTransaction);
    assertTrue(updatedTransaction.getNote().equals("chicken rice"));

    Transaction oldTransaction = SUT.findById(Long.valueOf(1)).get();
    oldTransaction.setAmount(BigDecimal.ZERO);

    Transaction updatedOldTransaction = SUT.save(oldTransaction);

    assertNotNull(updatedOldTransaction);
    assertTrue(updatedOldTransaction.getAmount().compareTo(BigDecimal.ZERO) == 0);
  }

  @Test
  @Override
  public void testDelete() {
    Transaction newTransaction = new Transaction();
    newTransaction.setNote("fried rice");
    newTransaction.setAmount(new BigDecimal(10.50));
    newTransaction.setCategory(Mapper.toCategory(Long.valueOf(3)));
    newTransaction.setDate(LocalDate.now());

    Wallet wallet = new Wallet();
    wallet.setId(Long.valueOf(1));

    newTransaction.setWallet(wallet);
    Transaction savedTransaction = SUT.save(newTransaction);

    SUT.delete(savedTransaction);

    assertTrue(!SUT.findById(savedTransaction.getId()).isPresent());
  }
  
}
