package com.gma.challenge.beruang.transaction.util;

import com.gma.challenge.beruang.common.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.generated.dto.NewTransactionRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateTransactionRequestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {
    public static void validateNewTransactionRequestData(NewTransactionRequestData requestData) {
        if (requestData == null) {
            throw new IncompleteRequestDataException("Invalid request data");
        }

        String message = "Missing data field(s): ";
        List<String> dataFields = new ArrayList<>();

        if (requestData.getNote() == null
                || requestData.getNote().isEmpty()
                || requestData.getNote().isBlank()) {
            dataFields.add("note");
        }

        if (requestData.getAmount() == null) {
            dataFields.add("amount");
        }

        if (requestData.getDate() == null) {
            dataFields.add("date");
        }

        if (requestData.getCategoryId() == null) {
            dataFields.add("categoryId");
        }

        if (!dataFields.isEmpty()) {
            throw new IncompleteRequestDataException(
                    message.concat(Arrays.asList(dataFields).toString()));
        }
    }

    public static void validateUpdateTransactionRequestData(
            UpdateTransactionRequestData requestData) {
        if (requestData != null) {
            if (requestData.getNote() != null) {
                return;
            }

            if (requestData.getAmount() != null) {
                return;
            }

            if (requestData.getDate() != null) {
                return;
            }

            if (requestData.getCategoryId() != null) {
                return;
            }
        }

        throw new IncompleteRequestDataException("Missing data field(s) to be updated");
    }
}
