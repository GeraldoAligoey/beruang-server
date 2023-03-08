package com.gma.challenge.beruang.budget.util;

import com.gma.challenge.beruang.common.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.generated.dto.NewBudgetRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateBudgetRequestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

    public static void validateNewBudgetRequestData(NewBudgetRequestData requestData) {
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

        if (requestData.getPeriod() == null
                || requestData.getPeriod().isEmpty()
                || requestData.getPeriod().isBlank()) {
            dataFields.add("period");
        }

        if (requestData.getLimitAmount() == null) {
            dataFields.add("limitAmount");
        }

        if (requestData.getCategoryIds() == null || requestData.getCategoryIds().isEmpty()) {
            dataFields.add("categories");
        }

        if (!dataFields.isEmpty()) {
            throw new IncompleteRequestDataException(
                    message.concat(Arrays.asList(dataFields).toString()));
        }
    }

    public static void validateUpdateBudgetRequestData(UpdateBudgetRequestData requestData) {
        if (requestData != null) {
            if (requestData.getName() != null && !requestData.getName().isBlank()) {
                return;
            }

            if (requestData.getPeriod() != null && !requestData.getPeriod().isBlank()) {
                return;
            }

            if (requestData.getLimitAmount() != null) {
                return;
            }

            if (requestData.getCategoryIds() != null && !requestData.getCategoryIds().isEmpty()) {
                return;
            }
        }

        throw new IncompleteRequestDataException("Missing data field(s) to be updated");
    }
}
