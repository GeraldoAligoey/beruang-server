package com.gma.challenge.beruang.budget.util;

import com.gma.challenge.beruang.budget.model.Budget;
import com.gma.challenge.beruang.category.model.Category;
import com.gma.challenge.beruang.generated.dto.BudgetData;
import com.gma.challenge.beruang.generated.dto.CategoryData;
import com.gma.challenge.beruang.generated.dto.NewBudgetRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateBudgetRequestData;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.gma.challenge.beruang.category.util.Mapper.toCategoryData;
import static com.gma.challenge.beruang.wallet.util.Mapper.toWalletData;

public class Mapper {

    public static BudgetData toBudgetData(Budget budget) {
        BudgetData budgetData = new BudgetData();
        BeanUtils.copyProperties(budget, budgetData);
        budgetData.setWallet(toWalletData(budget.getWallet()));
        budgetData.setCategories(toCategoriesData(budget.getCategories()));

        return budgetData;
    }

    public static List<CategoryData> toCategoriesData(Collection<Category> categories) {
        if (categories != null) {
            return categories.stream()
                    .map(category -> toCategoryData(category))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public static Budget toBudget(NewBudgetRequestData newBudgetRequestData) {
        Budget budget = new Budget();
        BeanUtils.copyProperties(newBudgetRequestData, budget);

        return budget;
    }

    public static Budget updateBudget(Budget budget, UpdateBudgetRequestData requestData) {
        if (requestData.getName() != null && !requestData.getName().isBlank()) {
            budget.setName(requestData.getName());
        }

        if (requestData.getPeriod() != null && !requestData.getPeriod().isBlank()) {
            budget.setPeriod(requestData.getPeriod());
        }

        if (requestData.getLimitAmount() != null) {
            budget.setLimitAmount(requestData.getLimitAmount());
        }

        return budget;
    }
}
