package com.gma.challenge.beruang.category.util;

import com.gma.challenge.beruang.common.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.generated.dto.NewCategoryRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateCategoryRequestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {
    public static void validateNewCategoryRequestData(NewCategoryRequestData requestData) {
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

        if (requestData.getColor() == null
                || requestData.getColor().isEmpty()
                || requestData.getColor().isBlank()) {
            dataFields.add("color");
        }

        if (requestData.getExpense() == null) {
            dataFields.add("expense");
        }

        if (requestData.getIcon() == null
                || requestData.getIcon().isEmpty()
                || requestData.getIcon().isBlank()) {
            dataFields.add("icon");
        }

        if (!dataFields.isEmpty()) {
            throw new IncompleteRequestDataException(
                    message.concat(Arrays.asList(dataFields).toString()));
        }
    }

    public static void validateUpdateCategoryRequestData(UpdateCategoryRequestData requestData) {
        if (requestData != null) {
            if (requestData.getName() != null && !requestData.getName().isBlank()) {
                return;
            }

            if (requestData.getColor() != null && !requestData.getColor().isBlank()) {
                return;
            }

            if (requestData.getExpense() != null) {
                return;
            }

            if (requestData.getIcon() != null && !requestData.getIcon().isBlank()) {
                return;
            }
        }

        throw new IncompleteRequestDataException("Missing data field(s) to be updated");
    }
}
