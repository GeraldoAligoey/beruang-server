package com.gma.challenge.beruang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateWalletRequestData;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;

public class Validator {

  public static void validateNewCategoryRequestData(NewCategoryRequestData requestData) {
    String message = "Missing data field(s): ";
    List<String> dataFields = new ArrayList<>();

    if (requestData.getName() == null || requestData.getName().isEmpty() || requestData.getName().isBlank()) {
      dataFields.add("name");
    }

    if (requestData.getColor() == null || requestData.getColor().isEmpty() || requestData.getColor().isBlank()) {
      dataFields.add("color");
    }

    if (requestData.getExpense() == null) {
      dataFields.add("expense");
    }

    if (requestData.getIcon() == null || requestData.getIcon().isEmpty() || requestData.getIcon().isBlank()) {
      dataFields.add("icon");
    }

    if (!dataFields.isEmpty()) {
      throw new IncompleteRequestDataException(message.concat(Arrays.asList(dataFields).toString()));
    }
  }

  public static void validateUpdateCategoryRequestData(UpdateCategoryRequestData requestData) {
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

    throw new IncompleteRequestDataException("Missing data field(s) to be updated");
  }

  public static void validateNewWalletRequestData(NewWalletRequestData requestData) {
    String message = "Missing data field(s): ";
    List<String> dataFields = new ArrayList<>();

    if (requestData.getName() == null || requestData.getName().isEmpty() || requestData.getName().isBlank()) {
      dataFields.add("name");
    }

    if (requestData.getDefaultCurrencyCode() == null || requestData.getDefaultCurrencyCode().isEmpty()
        || requestData.getDefaultCurrencyCode().isBlank()) {
      dataFields.add("defaultCurrencyCode");
    }

    if (requestData.getCategoryIds() == null || requestData.getCategoryIds().isEmpty()) {
      dataFields.add("categories");
    }

    if (!dataFields.isEmpty()) {
      throw new IncompleteRequestDataException(message.concat(Arrays.asList(dataFields).toString()));
    }
  }

  public static void validateUpdateWalletRequestData(UpdateWalletRequestData requestData) {
    if (requestData.getName() != null && !requestData.getName().isBlank()) {
      return;
    }

    if (requestData.getDefaultCurrencyCode() != null && !requestData.getDefaultCurrencyCode().isBlank()) {
      return;
    }

    if (requestData.getDefaultWallet() != null) {
      return;
    }

    if (requestData.getInitialBalanceAmount() == null) {
      return;
    }

    if (requestData.getCategoryIds() == null && !requestData.getCategoryIds().isEmpty()) {
      return;
    }

    throw new IncompleteRequestDataException("Missing data field(s) to be updated");
  }

}
