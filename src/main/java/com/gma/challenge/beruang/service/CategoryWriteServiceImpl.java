package com.gma.challenge.beruang.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.data.NewCategoryRequestData;
import com.gma.challenge.beruang.data.UpdateCategoryData;
import com.gma.challenge.beruang.data.UpdateCategoryRequestData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.repo.CategoryRepository;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.util.Mapper;
import com.gma.challenge.beruang.util.Validator;

@Transactional
@Service
public class CategoryWriteServiceImpl implements CategoryWriteService {

  private final CategoryRepository categoryRepository;
  private final WalletRepository walletRepository;

  @Autowired
  public CategoryWriteServiceImpl(CategoryRepository categoryRepository,
      WalletRepository walletRepository) {
    this.categoryRepository = categoryRepository;
    this.walletRepository = walletRepository;
  }

  @Override
  public CategoryResponseData createCategory(NewCategoryRequestData newCategoryRequestData) {
    Validator.validateNewCategoryRequestData(newCategoryRequestData);
    Category category = categoryRepository.saveAndFlush(Mapper.toCategory(newCategoryRequestData));

    CategoryResponseData categoryResponseData = new CategoryResponseData();
    categoryResponseData.category(Mapper.toCategoryData(category));

    return categoryResponseData;
  }

  @Override
  public CategoryResponseData updateCategory(Long id, UpdateCategoryRequestData updateCategoryRequestData) {
    Validator.validateUpdateCategoryRequestData(updateCategoryRequestData);
    UpdateCategoryData updateCategoryData = updateCategoryRequestData.getCategory();

    try {
      Category category = categoryRepository.getReferenceById(id);
      CategoryData categoryData = Mapper
          .toCategoryData(categoryRepository.saveAndFlush(Mapper.updateCategory(category, updateCategoryData)));

      CategoryResponseData categoryResponseData = new CategoryResponseData();
      categoryResponseData.category(categoryData);

      return categoryResponseData;

    } catch (EntityNotFoundException ex) {
      throw new CategoryNotFoundException("Invalid category id");
    }
  }

  @Override
  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Invalid category id"));

    List<Wallet> wallets = walletRepository.findByCategoryIds(Arrays.asList(id));

    for (Wallet wallet : wallets) {
      wallet.removeCategory(category);
      walletRepository.saveAndFlush(wallet);
    }

    categoryRepository.delete(category);
  }

}
