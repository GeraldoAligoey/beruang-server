package com.gma.challenge.beruang.service;

import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.CategoryNotInWalletException;
import com.gma.challenge.beruang.exception.TransactionNotFoundException;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.generated.dto.NewTransactionRequestData;
import com.gma.challenge.beruang.generated.dto.TransactionResponseData;
import com.gma.challenge.beruang.generated.dto.UpdateTransactionRequestData;
import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.model.Transaction;
import com.gma.challenge.beruang.model.Wallet;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.repo.TransactionRepository;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.service.util.Mapper;
import com.gma.challenge.beruang.service.util.Validator;
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
