package com.gma.challenge.beruang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.NewWalletData;
import com.gma.challenge.beruang.data.NewWalletRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateWalletData;
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

  public static void validateUpdateCategoryRequestData(UpdateCategoryRequestData updateCategoryRequestData) {
    String message = "Missing data field(s): ";
    List<String> dataFields = new ArrayList<>();

    UpdateCategoryData requestData = updateCategoryRequestData.getCategory();

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

  public static void validateNewWalletRequestData(NewWalletRequestData newWalletRequestData) {
    String message = "Missing data field(s): ";
    List<String> dataFields = new ArrayList<>();

    NewWalletData requestData = newWalletRequestData.getWallet();

    if (requestData.getName() == null || requestData.getName().isEmpty() || requestData.getName().isBlank()) {
      dataFields.add("name");
    }

    if (requestData.getDefaultCurrencyCode() == null || requestData.getDefaultCurrencyCode().isEmpty() || requestData.getDefaultCurrencyCode().isBlank()) {
      dataFields.add("defaultCurrencyCode");
    }

    if (requestData.getCategories() == null || requestData.getCategories().isEmpty()) {
      dataFields.add("categories");
    }

    if (!dataFields.isEmpty()) {
      throw new IncompleteRequestDataException(message.concat(Arrays.asList(dataFields).toString()));
    }
  }

  public static void validateUpdateWalletRequestData(UpdateWalletRequestData updateWalletRequestData) {
    UpdateWalletData updateWalletData = updateWalletRequestData.getWallet();

    if (updateWalletData.getName() != null && !updateWalletData.getName().isBlank()) {
      return;      
    }

    if (updateWalletData.getDefaultCurrencyCode() != null && !updateWalletData.getDefaultCurrencyCode().isBlank()) {
      return;      
    }

    if (updateWalletData.getDefaultWallet() != null) {
      return;      
    }

    if (updateWalletData.getInitialBalanceAmount() == null) {
      return;      
    }

    if (updateWalletData.getCategories() == null && !updateWalletData.getCategories().isEmpty()) {
      return;      
    }

    throw new IncompleteRequestDataException("Missing data field(s) to be updated");
  }

}
