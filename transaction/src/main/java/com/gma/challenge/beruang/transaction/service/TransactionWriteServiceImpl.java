package com.gma.challenge.beruang.transaction.service;

import com.gma.challenge.beruang.category.model.Category;
import com.gma.challenge.beruang.category.repo.CategoryRepository;
import com.gma.challenge.beruang.common.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.common.exception.CategoryNotInWalletException;
import com.gma.challenge.beruang.common.exception.TransactionNotFoundException;
import com.gma.challenge.beruang.common.exception.WalletNotFoundException;
import com.gma.challenge.beruang.generated.dto.NewTransactionRequestData;
import com.gma.challenge.beruang.generated.dto.TransactionResponseData;
import com.gma.challenge.beruang.generated.dto.UpdateTransactionRequestData;
import com.gma.challenge.beruang.transaction.model.Transaction;
import com.gma.challenge.beruang.transaction.repo.TransactionRepository;
import com.gma.challenge.beruang.transaction.util.Mapper;
import com.gma.challenge.beruang.transaction.util.Validator;
import com.gma.challenge.beruang.wallet.model.Wallet;
import com.gma.challenge.beruang.wallet.repo.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TransactionWriteServiceImpl implements TransactionWriteService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final CategoryRepository categoryRepository;

    public TransactionWriteServiceImpl(
            TransactionRepository transactionRepository,
            WalletRepository walletRepository,
            CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TransactionResponseData createTransaction(
            Long walletId, NewTransactionRequestData newTransactionRequestData) {
        Validator.validateNewTransactionRequestData(newTransactionRequestData);
        Transaction transaction = Mapper.toTransaction(newTransactionRequestData);

        Wallet wallet =
                walletRepository
                        .findById(walletId)
                        .orElseThrow(() -> new WalletNotFoundException("Invalid wallet id"));

        Category category =
                categoryRepository
                        .findById(newTransactionRequestData.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException("Invalid category id"));

        if (!wallet.getCategories().contains(category)) {
            throw new CategoryNotInWalletException(
                    "The given category id is not part of the categories in the selected wallet");
        }

        transaction.setWallet(wallet);
        transaction.setCategory(category);

        return TransactionResponseData.builder()
                .transaction(
                        Mapper.toTransactionData(transactionRepository.saveAndFlush(transaction)))
                .build();
    }

    @Override
    public TransactionResponseData updateTransaction(
            Long walletId,
            Long transactionId,
            UpdateTransactionRequestData updateTransactionRequestData) {
        Validator.validateUpdateTransactionRequestData(updateTransactionRequestData);
        Transaction transaction =
                transactionRepository
                        .findById(transactionId)
                        .orElseThrow(
                                () -> new TransactionNotFoundException("Invalid transaction id"));

        if (!transaction.getWallet().getId().equals(walletId)) {
            throw new WalletNotFoundException("Invalid wallet id");
        }

        if (updateTransactionRequestData.getCategoryId() != null) {
            Category category =
                    categoryRepository
                            .findById(updateTransactionRequestData.getCategoryId())
                            .orElseThrow(
                                    () -> new CategoryNotFoundException("Invalid category id"));

            transaction.setCategory(category);
        }

        return TransactionResponseData.builder()
                .transaction(
                        Mapper.toTransactionData(
                                transactionRepository.saveAndFlush(
                                        Mapper.updateTransaction(
                                                transaction, updateTransactionRequestData))))
                .build();
    }

    @Override
    public void deleteTransaction(Long walletId, Long transactionId) {
        Transaction transaction =
                transactionRepository
                        .findById(transactionId)
                        .orElseThrow(
                                () -> new TransactionNotFoundException("Invalid transaction id"));

        if (!transaction.getWallet().getId().equals(walletId)) {
            throw new WalletNotFoundException("Invalid wallet id");
        }

        transactionRepository.delete(transaction);
    }
}
