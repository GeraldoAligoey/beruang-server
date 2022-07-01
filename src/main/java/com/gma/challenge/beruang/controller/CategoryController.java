package com.gma.challenge.beruang.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.gma.challenge.beruang.api.CategoriesApi;
import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.service.CategoryReadService;
import com.gma.challenge.beruang.service.CategoryWriteService;

@Controller
public class CategoryController implements CategoriesApi {

  private final CategoryReadService categoryReadService;
  private final CategoryWriteService categoryWriteService;

  public CategoryController(CategoryReadService categoryReadService,
      CategoryWriteService categoryWriteService) {
    this.categoryReadService = categoryReadService;
    this.categoryWriteService = categoryWriteService;
  }

  @Override
  public ResponseEntity<CategoryResponseData> createCategory(@Valid NewCategoryRequestData newCategoryRequestData) {
    return ResponseEntity.ok(categoryWriteService.createCategory(newCategoryRequestData));
  }

  @Override
  public ResponseEntity<Void> deleteCategory(Long id) {
    categoryWriteService.deleteCategory(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CategoryResponseData> findCategory(Long id) {
    return ResponseEntity.ok(categoryReadService.findCategory(id));
  }

  @Override
  public ResponseEntity<CategoriesResponseData> findCategories() {
    return ResponseEntity.ok(categoryReadService.findCategories());
  }

  @Override
  public ResponseEntity<CategoryResponseData> updateCategory(Long id,
      @Valid UpdateCategoryRequestData updateCategoryRequestData) {
    return ResponseEntity.ok(categoryWriteService.updateCategory(id, updateCategoryRequestData));
  }

}
