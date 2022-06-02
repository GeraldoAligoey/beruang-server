package com.gma.challenge.beruang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gma.challenge.beruang.data.NewCategoryRequestData;
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

    throw new IncompleteRequestDataException(message.concat(Arrays.asList(dataFields).toString()));
  }
}
