package com.gma.challenge.beruang.wallet.util;

import com.gma.challenge.beruang.common.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.generated.dto.NewWalletRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateWalletRequestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {
    public static void validateNewWalletRequestData(NewWalletRequestData requestData) {
        if (requestData == null) {
            throw new IncompleteRequestDataException("Invalid request data");
        }

        String message = "Missing data field(s): ";
        List<String> dataFields = new ArrayList<>();

        if (requestData.getName() == null
                || requestData.getName().isEmpty()
                || requestData.getName().isBlank()) {
            dataFields.add("name");
        }

        if (requestData.getDefaultCurrencyCode() == null
                || requestData.getDefaultCurrencyCode().isEmpty()
                || requestData.getDefaultCurrencyCode().isBlank()) {
            dataFields.add("defaultCurrencyCode");
        }

        if (requestData.getCategoryIds() == null || requestData.getCategoryIds().isEmpty()) {
            dataFields.add("categories");
        }

        if (!dataFields.isEmpty()) {
            throw new IncompleteRequestDataException(
                    message.concat(Arrays.asList(dataFields).toString()));
        }
    }

    public static void validateUpdateWalletRequestData(UpdateWalletRequestData requestData) {
        if (requestData != null) {
            if (requestData.getName() != null && !requestData.getName().isBlank()) {
                return;
            }

            if (requestData.getDefaultCurrencyCode() != null
                    && !requestData.getDefaultCurrencyCode().isBlank()) {
                return;
            }

            if (requestData.getInitialBalanceAmount() != null) {
                return;
            }

            if (requestData.getCategoryIds() != null && !requestData.getCategoryIds().isEmpty()) {
                return;
            }
        }

        throw new IncompleteRequestDataException("Missing data field(s) to be updated");
    }
}
