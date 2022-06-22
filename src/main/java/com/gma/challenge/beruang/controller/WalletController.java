package com.gma.challenge.beruang.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.gma.challenge.beruang.api.WalletsApi;
import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.BudgetsResponseData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.data.NewTransactionRequestData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.TransactionResponseData;
import com.gma.challenge.beruang.data.TransactionsResponseData;
import com.gma.challenge.beruang.data.UpdateBudgetRequestData;
import com.gma.challenge.beruang.data.UpdateTransactionRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;
import com.gma.challenge.beruang.service.BudgetReadService;
import com.gma.challenge.beruang.service.BudgetWriteService;
import com.gma.challenge.beruang.service.TransactionReadService;
import com.gma.challenge.beruang.service.TransactionWriteService;
import com.gma.challenge.beruang.service.WalletReadService;
import com.gma.challenge.beruang.service.WalletWriteService;

@Controller
public class WalletController implements WalletsApi {

  private final WalletReadService walletReadService;
  private final WalletWriteService walletWriteService;
  private final BudgetReadService budgetReadService;
  private final BudgetWriteService budgetWriteService;
  private final TransactionReadService transactionReadService;
  private final TransactionWriteService transactionWriteService;

  @Autowired
  public WalletController(WalletReadService walletReadService, WalletWriteService walletWriteService,
      BudgetReadService budgetReadService, BudgetWriteService budgetWriteService,
      TransactionReadService transactionReadService, TransactionWriteService transactionWriteService) {
    this.walletReadService = walletReadService;
    this.walletWriteService = walletWriteService;
    this.budgetReadService = budgetReadService;
    this.budgetWriteService = budgetWriteService;
    this.transactionReadService = transactionReadService;
    this.transactionWriteService = transactionWriteService;
  }

  @Override
  public ResponseEntity<WalletResponseData> createWallet(@Valid NewWalletRequestData newWalletRequestData) {
    return ResponseEntity.ok(walletWriteService.createWallet(newWalletRequestData));
  }

  @Override
  public ResponseEntity<WalletResponseData> updateWallet(Long walletId,
      @Valid UpdateWalletRequestData updateWalletRequestData) {
    return ResponseEntity.ok(walletWriteService.updateWallet(walletId, updateWalletRequestData));
  }

  @Override
  public ResponseEntity<Void> deleteWallet(Long walletId) {
    walletWriteService.deleteWallet(walletId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<WalletResponseData> findWallet(Long walletId) {
    return ResponseEntity.ok(walletReadService.findWallet(walletId));
  }

  @Override
  public ResponseEntity<WalletsResponseData> findWallets() {
    return ResponseEntity.ok(walletReadService.findWallets());
  }

  @Override
  public ResponseEntity<BudgetResponseData> createBudget(Long walletId,
      @Valid NewBudgetRequestData newBudgetRequestData) {
    return ResponseEntity.ok(budgetWriteService.createBudget(walletId, newBudgetRequestData));
  }

  @Override
  public ResponseEntity<BudgetResponseData> updateBudget(Long walletId, Long budgetId,
      @Valid UpdateBudgetRequestData updateBudgetRequestData) {
    return ResponseEntity.ok(budgetWriteService.updateBudget(walletId, budgetId, updateBudgetRequestData));
  }

  @Override
  public ResponseEntity<Void> deleteBudget(Long walletId, Long budgetId) {
    budgetWriteService.deleteBudget(walletId, budgetId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<BudgetResponseData> findBudget(Long walletId, Long budgetId) {
    return ResponseEntity.ok(budgetReadService.findBudget(walletId, budgetId));
  }

  @Override
  public ResponseEntity<BudgetsResponseData> findBudgets(Long walletId) {
    return ResponseEntity.ok(budgetReadService.findBudgets(walletId));
  }

  @Override
  public ResponseEntity<TransactionResponseData> createTransaction(Long walletId,
      @Valid NewTransactionRequestData newTransactionRequestData) {
    return ResponseEntity.ok(transactionWriteService.createTransaction(walletId, newTransactionRequestData));
  }

  @Override
  public ResponseEntity<TransactionResponseData> updateTransaction(Long walletId, Long transactionId,
      @Valid UpdateTransactionRequestData updateTransactionRequestData) {
    return ResponseEntity.ok(transactionWriteService.updateTransaction(walletId, transactionId, updateTransactionRequestData));
  }

  @Override
  public ResponseEntity<Void> deleteTransaction(Long walletId, Long transactionId) {
    transactionWriteService.deleteTransaction(walletId, transactionId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TransactionResponseData> findTransaction(Long walletId, Long transactionId) {
    return ResponseEntity.ok(transactionReadService.findTransaction(walletId, transactionId));
  }

  /**
   * @deprecated Replaced by {@link #findTransactions(Long, LocalDate, LocalDate, BigDecimal, BigDecimal)}
   */
  @Deprecated
  public ResponseEntity<TransactionsResponseData> findTransactions(Long walletId) {
    return ResponseEntity.ok(transactionReadService.findTransactions(walletId));
  }

  @Override
  public ResponseEntity<TransactionsResponseData> findTransactions(Long walletId, @Valid LocalDate fromDate,
      @Valid LocalDate toDate, BigDecimal fromAmount, BigDecimal toAmount) {
    return ResponseEntity.ok(transactionReadService.findTransactions(walletId, fromDate, toDate, fromAmount, toAmount));
  }

}
