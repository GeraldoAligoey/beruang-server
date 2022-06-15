package com.gma.challenge.beruang.helper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.gma.challenge.beruang.data.BudgetData;
import com.gma.challenge.beruang.data.BudgetResponseData;
import com.gma.challenge.beruang.data.NewBudgetRequestData;
import com.gma.challenge.beruang.domain.Period;

public class BudgetHelper {

  private static final List<Long> BUDGET_CATEGORY_IDS = Arrays.asList(Long.valueOf(1), Long.valueOf(2));
  private static final BigDecimal BUDGET_LIMIT_AMOUNT = new BigDecimal(5000);
  private static final String BUDGET_PERIOD = Period.MONTHLY.getValue();
  private static final String BUDGET_NAME = "Budget Name";

  public static NewBudgetRequestData getValidNewBudgetRequestDataSample() {
    NewBudgetRequestData requestData = NewBudgetRequestData.builder()
        .name(BUDGET_NAME)
        .period(BUDGET_PERIOD)
        .limitAmount(BUDGET_LIMIT_AMOUNT)
        .categoryIds(BUDGET_CATEGORY_IDS)
        .build();
    return requestData;
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

}
