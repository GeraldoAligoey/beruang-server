package com.gma.challenge.beruang.transaction.util;

import com.gma.challenge.beruang.generated.dto.NewTransactionRequestData;
import com.gma.challenge.beruang.generated.dto.TransactionData;
import com.gma.challenge.beruang.generated.dto.UpdateTransactionRequestData;
import com.gma.challenge.beruang.transaction.model.Transaction;
import org.springframework.beans.BeanUtils;

import static com.gma.challenge.beruang.category.util.Mapper.toCategoryData;
import static com.gma.challenge.beruang.wallet.util.Mapper.toWalletData;

public class Mapper {
    public static TransactionData toTransactionData(Transaction transaction) {
        TransactionData transactionData = new TransactionData();
        BeanUtils.copyProperties(transaction, transactionData);

        transactionData.category(toCategoryData(transaction.getCategory()));
        transactionData.wallet(toWalletData(transaction.getWallet()));

        return transactionData;
    }

    public static Transaction toTransaction(NewTransactionRequestData newTransactionRequestData) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(newTransactionRequestData, transaction);

        return transaction;
    }

    public static Transaction updateTransaction(
            Transaction transaction, UpdateTransactionRequestData requestData) {

        if (requestData.getNote() != null) {
            transaction.setNote(requestData.getNote());
        }

        if (requestData.getAmount() != null) {
            transaction.setAmount(requestData.getAmount());
        }

        if (requestData.getDate() != null) {
            transaction.setDate(requestData.getDate());
        }

        return transaction;
    }
}
