package com.gma.challenge.beruang.unit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gma.challenge.beruang.common.ControllerTest;
import com.gma.challenge.beruang.common.helper.CategoryHelper;
import com.gma.challenge.beruang.controller.CategoryController;
import com.gma.challenge.beruang.data.CategoriesResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.service.CategoryReadService;
import com.gma.challenge.beruang.service.CategoryWriteService;
import com.gma.challenge.beruang.util.Mapper;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest implements ControllerTest {

  private final Logger LOG = LoggerFactory.getLogger(CategoryControllerTest.class);
  private static final String CATEGORIES_URL = "/categories";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CategoryReadService categoryReadService;

  @MockBean
  private CategoryWriteService categoryWriteService;

  @Override
  public void testCreate_validNewRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testCreate_invalidEmptyNewRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testCreate_invalidIncompleteNewRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testCreate_invalidNullNewRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_validId_validFullRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_validId_validPartialRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_validId_invalidEmptyRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_validId_invalidNullRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_invalidId_validFullRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_invalidId_validPartialRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_invalidId_invalidEmptyRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testUpdate_invalidId_invalidNullRequestData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testDelete_validId_linked() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testDelete_validId_unlinked() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testDelete_invalidId() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testFindRecord_validId_recordExist() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testFindRecord_validId_recordNotExist() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testFindRecord_invalidId() {
    // TODO Auto-generated method stub

  }

  @Override
  public void testFindRecords_empty() throws Exception {
    // TODO Auto-generated method stub

  }

  @Test
  @Override
  public void testFindRecords_single() throws Exception {
    Category category = CategoryHelper.getCategorySample(true);
    CategoriesResponseData responseData = CategoriesResponseData.builder()
        .categories(Arrays.asList(Mapper.toCategoryData(category))).build();
    when(categoryReadService.findCategories()).thenReturn(responseData);

    RequestBuilder request = MockMvcRequestBuilders.get(CATEGORIES_URL).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{categories:[{}]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Override
  public void testFindRecords_multiple() throws Exception {
    // TODO Auto-generated method stub

  }

}
