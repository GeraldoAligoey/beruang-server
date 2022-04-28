package com.gma.challenge.beruang.controller;

import java.util.List;
import java.util.Set;

import com.gma.challenge.beruang.dto.CategoryData;
import com.gma.challenge.beruang.model.Category;
import com.gma.challenge.beruang.service.ReadCategoryService;
import com.gma.challenge.beruang.service.WriteCategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/category")
public class CategoryRestController {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryRestController.class);

  private final WriteCategoryService writeCategoryService;
  private final ReadCategoryService readCategoryService;

  @Autowired
  public CategoryRestController(WriteCategoryService writeCategoryService, ReadCategoryService readCategoryService) {
    this.writeCategoryService = writeCategoryService;
    this.readCategoryService = readCategoryService;
  }

  @PostMapping("/new")
  public Category postMethodName(@RequestBody CategoryData entity) {
    LOG.info("entity {}", entity.toString());
    
    return writeCategoryService.createCategory(entity);
  }

  @GetMapping()
  public List<Category> findAll() {

    return readCategoryService.findAll();
  }

  @GetMapping("/{categoryId}")
  public Category findById(@PathVariable String categoryId) { 

    return readCategoryService.findById(Long.valueOf(categoryId));
  }
  
  @GetMapping("/wallet/{walletId}")
  public Set<Category> findAllByWalletId(@PathVariable String walletId) {

    return readCategoryService.findAllByWalletId(Long.valueOf(walletId));
  }

  @GetMapping("/budget/{budgetId}")
  public Set<Category> findAllByBudgetId(@PathVariable String budgetId) {

    return readCategoryService.findAllByBudgetId(Long.valueOf(budgetId));
  }
}
