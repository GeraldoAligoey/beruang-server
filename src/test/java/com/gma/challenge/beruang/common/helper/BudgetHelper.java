package com.gma.challenge.beruang.common.helper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.gma.challenge.beruang.data.BudgetData;
import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.data.UpdateBudgetRequestData;
import com.gma.challenge.beruang.domain.Period;

public class BudgetHelper {

  private static final List<Long> BUDGET_CATEGORY_IDS = Arrays.asList(Long.valueOf(1), Long.valueOf(2));
  private static final BigDecimal BUDGET_LIMIT_AMOUNT = new BigDecimal(5000);
  private static final String BUDGET_PERIOD = Period.MONTHLY.getValue();
  private static final String BUDGET_NAME = "Budget Name";

  private static final List<Long> UPDATE_BUDGET_CATEGORY_IDS = Arrays.asList(Long.valueOf(3), Long.valueOf(4));
  private static final BigDecimal UPDATE_BUDGET_LIMIT_AMOUNT = new BigDecimal(500);
  private static final String UPDATE_BUDGET_PERIOD = Period.WEEKLY.getValue();
  private static final String UPDATE_BUDGET_NAME = "Update Budget Name";

  public static NewBudgetRequestData getValidNewBudgetRequestDataSample() {
    return NewBudgetRequestData.builder()
        .name(BUDGET_NAME)
        .period(BUDGET_PERIOD)
        .limitAmount(BUDGET_LIMIT_AMOUNT)
        .categoryIds(BUDGET_CATEGORY_IDS)
        .build();
  }

  public static boolean isBudgetResponseDataEqualsToSample(BudgetResponseData responseData) {
    BudgetData budgetData = responseData.getBudget();
    if (!budgetData.getName().equals(BUDGET_NAME)) {
      return false;
    }

    if (!budgetData.getPeriod().equals(BUDGET_PERIOD)) {
      return false;
    }

    if (!budgetData.getLimitAmount().equals(BUDGET_LIMIT_AMOUNT)) {
      return false;
    }

    if (!UtilHelper.isEquals(budgetData.getCategories(), BUDGET_CATEGORY_IDS)) {
      return false;
    }

    return true;
  }

  public static NewBudgetRequestData getInvalidIncompleteNewBudgetRequestDataSample() {
    return NewBudgetRequestData.builder()
        .name(BUDGET_NAME)
        .limitAmount(BUDGET_LIMIT_AMOUNT)
        .categoryIds(BUDGET_CATEGORY_IDS)
        .build();
  }

  public static NewBudgetRequestData getInvalidEmptyNewBudgetRequestDataSample() {
    return NewBudgetRequestData.builder().build();
  }

  public static NewBudgetRequestData getInvalidNullNewBudgetRequestDataSample() {
    return null;
  }

  public static UpdateBudgetRequestData getValidFullUpdateBudgetRequestDataSample() {
    return UpdateBudgetRequestData.builder()
        .name(UPDATE_BUDGET_NAME)
        .period(UPDATE_BUDGET_PERIOD)
        .limitAmount(UPDATE_BUDGET_LIMIT_AMOUNT)
        .categoryIds(UPDATE_BUDGET_CATEGORY_IDS)
        .build();
  }

  public static UpdateBudgetRequestData getValidPartialUpdateBudgetRequestDataSample() {
    return UpdateBudgetRequestData.builder()
        .period(UPDATE_BUDGET_PERIOD)
        .limitAmount(UPDATE_BUDGET_LIMIT_AMOUNT)
        .build();
  }

  public static UpdateBudgetRequestData getInvalidEmptyUpdateBudgetRequestDataSample() {
    return UpdateBudgetRequestData.builder()
        .build();
  }

  public static UpdateBudgetRequestData getInvalidNullUpdateBudgetRequestDataSample() {
    return null;
  }

  public static boolean isUpdateBudgetResponseDataEqualsToSample(BudgetResponseData responseData) {
    BudgetData budgetData = responseData.getBudget();
    if (!budgetData.getName().equals(UPDATE_BUDGET_NAME)) {
      return false;
    }

    if (!budgetData.getPeriod().equals(UPDATE_BUDGET_PERIOD)) {
      return false;
    }

    if (!budgetData.getLimitAmount().equals(UPDATE_BUDGET_LIMIT_AMOUNT)) {
      return false;
    }

    if (!UtilHelper.isEquals(budgetData.getCategories(), UPDATE_BUDGET_CATEGORY_IDS)) {
      return false;
    }

    return true;
  }

	public static boolean isUpdateBudgetResponseDataEqualsToPartialSample(BudgetResponseData responseData) {
    BudgetData budgetData = responseData.getBudget();
    if (!budgetData.getPeriod().equals(UPDATE_BUDGET_PERIOD)) {
      return false;
    }

    if (!budgetData.getLimitAmount().equals(UPDATE_BUDGET_LIMIT_AMOUNT)) {
      return false;
    }

    return true;
	}

  public static UpdateBudgetRequestData getValidPartialUpdateBudgetInvalidCategoryIdsRequestDataSample() {
    return UpdateBudgetRequestData.builder()
        .categoryIds(Arrays.asList(Long.valueOf(6), Long.valueOf(7)))
        .build();
  }

  public static UpdateBudgetRequestData getValidPartialUpdateBudgetValidCategoryIdsRequestDataSample() {
    return UpdateBudgetRequestData.builder()
        .categoryIds(Arrays.asList(Long.valueOf(1), Long.valueOf(7)))
        .build();
  }

}
