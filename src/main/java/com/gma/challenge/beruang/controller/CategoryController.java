package com.gma.challenge.beruang.controller;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import com.gma.challenge.beruang.api.CategoriesApi;
import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.service.CategoryReadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

@Controller
public class CategoryController implements CategoriesApi {

  private final CategoryReadService categoryReadService;

  @Autowired
  public CategoryController(CategoryReadService categoryReadService) {
    this.categoryReadService = categoryReadService;
  }

  @Override
  public ResponseEntity<CategoryResponseData> createCategory(@Valid NewCategoryRequestData newCategoryRequestData) {
    // TODO Auto-generated method stub
    return CategoriesApi.super.createCategory(newCategoryRequestData);
  }

  @Override
  public ResponseEntity<Void> deleteCategory(BigDecimal id) {
    // TODO Auto-generated method stub
    return CategoriesApi.super.deleteCategory(id);
  }

  @Override
  public ResponseEntity<CategoryResponseData> findCategory(BigDecimal id) {
    // TODO Auto-generated method stub
    return CategoriesApi.super.findCategory(id);
  }

  @Override
  public ResponseEntity<CategoriesResponseData> getCategories() {
    return ResponseEntity.ok(categoryReadService.getCategories());
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    // TODO Auto-generated method stub
    return CategoriesApi.super.getRequest();
  }

  @Override
  public ResponseEntity<CategoryResponseData> updateCategory(BigDecimal id,
      @Valid UpdateCategoryRequestData updateCategoryRequestData) {
    // TODO Auto-generated method stub
    return CategoriesApi.super.updateCategory(id, updateCategoryRequestData);
  }
  
}
