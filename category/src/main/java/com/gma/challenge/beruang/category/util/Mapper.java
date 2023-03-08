package com.gma.challenge.beruang.category.util;

import com.gma.challenge.beruang.category.model.Category;
import com.gma.challenge.beruang.generated.dto.CategoryData;
import com.gma.challenge.beruang.generated.dto.NewCategoryRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateCategoryRequestData;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static CategoryData toCategoryData(Category category) {
        return CategoryData.builder()
                .id(category.getId())
                .name(category.getName())
                .expense(category.isExpense())
                .icon(category.getIcon())
                .color(category.getColor())
                .active(category.isActive())
                .build();
    }

    public static List<CategoryData> toCategoriesData(List<Category> categories) {
        if (categories != null) {
            return categories.stream()
                    .map(category -> toCategoryData(category))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public static Category updateCategory(
            Category category, UpdateCategoryRequestData requestData) {
        if (requestData.getName() != null && !requestData.getName().isBlank()) {
            category.setName(requestData.getName());
        }

        if (requestData.getExpense() != null) {
            category.setExpense(requestData.getExpense());
        }

        if (requestData.getIcon() != null && !requestData.getIcon().isBlank()) {
            category.setIcon(requestData.getIcon());
        }

        if (requestData.getColor() != null && !requestData.getColor().isBlank()) {
            category.setColor(requestData.getColor());
        }

        return category;
    }

    public static Category toCategory(NewCategoryRequestData requestData) {
        Category category = new Category();
        BeanUtils.copyProperties(requestData, category);

        return category;
    }

    public static Category toCategory(CategoryData categoryData) {
        Category category = new Category();
        category.setId(categoryData.getId());
        category.setName(categoryData.getName());
        category.setExpense(categoryData.getExpense());
        category.setIcon(categoryData.getIcon());
        category.setColor(categoryData.getColor());
        category.setActive(categoryData.getActive());

        return category;
    }

    public static Category toCategory(Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);

        return category;
    }
}
