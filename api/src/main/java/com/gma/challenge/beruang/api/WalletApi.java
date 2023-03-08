package com.gma.challenge.beruang.api;

import com.gma.challenge.beruang.generated.api.WalletsApi;
import com.gma.challenge.beruang.generated.dto.*;
import com.gma.challenge.beruang.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WalletApi implements WalletsApi {

    private final WalletReadService walletReadService;
    private final WalletWriteService walletWriteService;
    private final BudgetReadService budgetReadService;
    private final BudgetWriteService budgetWriteService;
    private final TransactionReadService transactionReadService;
    private final TransactionWriteService transactionWriteService;

    public WalletApi(
            WalletReadService walletReadService,
            WalletWriteService walletWriteService,
            BudgetReadService budgetReadService,
            BudgetWriteService budgetWriteService,
            TransactionReadService transactionReadService,
            TransactionWriteService transactionWriteService) {
        this.walletReadService = walletReadService;
        this.walletWriteService = walletWriteService;
        this.budgetReadService = budgetReadService;
        this.budgetWriteService = budgetWriteService;
        this.transactionReadService = transactionReadService;
        this.transactionWriteService = transactionWriteService;
    }

    @Override
    public ResponseEntity<WalletResponseData> createWallet(
            @Valid NewWalletRequestData newWalletRequestData) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(walletWriteService.createWallet(newWalletRequestData));
    }

    @Override
    public ResponseEntity<WalletResponseData> updateWallet(
            Long walletId, @Valid UpdateWalletRequestData updateWalletRequestData) {
        return ResponseEntity.ok(
                walletWriteService.updateWallet(walletId, updateWalletRequestData));
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
    public ResponseEntity<WalletResponseData> setDefaultTrue(Long walletId, @Valid Object body) {
        return ResponseEntity.ok(walletWriteService.setDefaultTrue(walletId));
    }

    @Override
    public ResponseEntity<BudgetResponseData> createBudget(
            Long walletId, @Valid NewBudgetRequestData newBudgetRequestData) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(budgetWriteService.createBudget(walletId, newBudgetRequestData));
    }

    @Override
    public ResponseEntity<BudgetResponseData> updateBudget(
            Long walletId, Long budgetId, @Valid UpdateBudgetRequestData updateBudgetRequestData) {
        return ResponseEntity.ok(
                budgetWriteService.updateBudget(walletId, budgetId, updateBudgetRequestData));
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
    public ResponseEntity<TransactionResponseData> createTransaction(
            Long walletId, @Valid NewTransactionRequestData newTransactionRequestData) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        transactionWriteService.createTransaction(
                                walletId, newTransactionRequestData));
    }

    @Override
    public ResponseEntity<TransactionResponseData> updateTransaction(
            Long walletId,
            Long transactionId,
            @Valid UpdateTransactionRequestData updateTransactionRequestData) {
        return ResponseEntity.ok(
                transactionWriteService.updateTransaction(
                        walletId, transactionId, updateTransactionRequestData));
    }

    @Override
    public ResponseEntity<Void> deleteTransaction(Long walletId, Long transactionId) {
        transactionWriteService.deleteTransaction(walletId, transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransactionResponseData> findTransaction(
            Long walletId, Long transactionId) {
        return ResponseEntity.ok(transactionReadService.findTransaction(walletId, transactionId));
    }

    /**
     * @deprecated Replaced by {@link #findTransactions(Long, LocalDate, LocalDate, BigDecimal,
     *     BigDecimal, List)}
     */
    @Deprecated
    public ResponseEntity<TransactionsResponseData> findTransactions(Long walletId) {
        return ResponseEntity.ok(transactionReadService.findTransactions(walletId));
    }

    @Override
    public ResponseEntity<TransactionsResponseData> findTransactions(
            Long walletId,
            @Valid LocalDate fromDate,
            @Valid LocalDate toDate,
            BigDecimal fromAmount,
            BigDecimal toAmount,
            @Valid List<Long> categoryIds) {
        return ResponseEntity.ok(
                transactionReadService.findTransactions(
                        walletId, fromDate, toDate, fromAmount, toAmount, categoryIds));
    }

    @Override
    public ResponseEntity<Void> moveTransactionToNewCategory(
            Long walletId,
            @NotNull @Valid Long oldCategoryId,
            @NotNull @Valid Long newCategoryId,
            @Valid Object body) {
        walletWriteService.moveTransactionToNewCategory(walletId, oldCategoryId, newCategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> moveTransactionToNewWallet(
            Long walletId, @NotNull @Valid Long newWalletId, @Valid Object body) {
        walletWriteService.moveTransactionToNewWallet(walletId, newWalletId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
