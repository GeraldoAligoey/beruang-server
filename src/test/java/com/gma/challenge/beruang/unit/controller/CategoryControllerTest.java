package com.gma.challenge.beruang.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.ArraySizeComparator;
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
import com.gma.challenge.beruang.data.CategoryData;
import com.gma.challenge.beruang.data.CategoryResponseData;
import com.gma.challenge.beruang.domain.Category;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;
import com.gma.challenge.beruang.exception.IncompleteRequestDataException;
import com.gma.challenge.beruang.service.CategoryReadService;
import com.gma.challenge.beruang.service.CategoryWriteService;
import com.gma.challenge.beruang.util.Mapper;
import com.google.gson.Gson;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest implements ControllerTest {

  private final Logger LOG = LoggerFactory.getLogger(CategoryControllerTest.class);
  private static final Long VALID_ID = Long.valueOf(1);
  private static final Long INVALID_ID = Long.valueOf(-1);
  private static final Long VALID_NORECORD_ID = Long.valueOf(100000);
  private static final String CATEGORIES_URL = "/categories";
  private static final String FIND_CATEGORY_VALID_ID_URL = CATEGORIES_URL + "/" + VALID_ID;
  private static final String FIND_CATEGORIES_INVALID_ID_URL = CATEGORIES_URL + "/" + INVALID_ID;
  private static final String FIND_CATEGORY_VALID_NORECORD_ID_URL = CATEGORIES_URL + "/" + VALID_NORECORD_ID;
  private static final String DELETE_CATEGORIES_INVALID_ID_URL = CATEGORIES_URL + "/" + INVALID_ID;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CategoryReadService categoryReadService;

  @MockBean
  private CategoryWriteService categoryWriteService;

  @Test
  @Override
  public void testCreate_validNewRequestData() throws Exception {
    CategoryResponseData responseData = CategoryResponseData.builder()
        .category(Mapper.toCategoryData(CategoryHelper.getCategorySample(true))).build();
    when(categoryWriteService.createCategory(any())).thenReturn(responseData);

    RequestBuilder request = MockMvcRequestBuilders
      .post(CATEGORIES_URL)
      .content(new Gson().toJson(CategoryHelper.getValidNewCategoryRequestDataSample()))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    LOG.info("URL: {}", CATEGORIES_URL);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{category:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testCreate_invalidEmptyNewRequestData() throws Exception {
    when(categoryWriteService.createCategory(CategoryHelper.getInvalidEmptyNewCategoryRequestDataSample())).thenThrow(new IncompleteRequestDataException("Invalid request data"));

    RequestBuilder request = MockMvcRequestBuilders
      .post(CATEGORIES_URL)
      .content(new Gson().toJson(CategoryHelper.getInvalidEmptyNewCategoryRequestDataSample()))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    LOG.info("URL: {}", CATEGORIES_URL);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{errors:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testCreate_invalidIncompleteNewRequestData() throws Exception {
    when(categoryWriteService.createCategory(CategoryHelper.getInvalidIncompleteNewCategoryRequestDataSample())).thenThrow(new IncompleteRequestDataException("Invalid request data"));

    RequestBuilder request = MockMvcRequestBuilders
      .post(CATEGORIES_URL)
      .content(new Gson().toJson(CategoryHelper.getInvalidIncompleteNewCategoryRequestDataSample()))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    LOG.info("URL: {}", CATEGORIES_URL);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{errors:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testCreate_invalidNullNewRequestData() throws Exception {
    when(categoryWriteService.createCategory(CategoryHelper.getInvalidNullNewCategoryRequestDataSample())).thenThrow(new IncompleteRequestDataException("Invalid request data"));

    RequestBuilder request = MockMvcRequestBuilders
      .post(CATEGORIES_URL)
      .content(new Gson().toJson(CategoryHelper.getInvalidNullNewCategoryRequestDataSample()))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    LOG.info("URL: {}", CATEGORIES_URL);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{errors:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
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

  @Test
  @Override
  public void testDelete_invalidId() throws Exception {
    doThrow(new CategoryNotFoundException("Invalid category id")).when(categoryWriteService).deleteCategory(INVALID_ID);

    RequestBuilder request = MockMvcRequestBuilders.delete(DELETE_CATEGORIES_INVALID_ID_URL)
        .accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{errors:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testFindRecord_validId_recordExist() throws Exception {
    Category category = CategoryHelper.getCategorySample(true);
    CategoryResponseData responseData = CategoryResponseData.builder().category(Mapper.toCategoryData(category))
        .build();

    when(categoryReadService.findCategory(VALID_ID)).thenReturn(responseData);

    RequestBuilder request = MockMvcRequestBuilders.get(FIND_CATEGORY_VALID_ID_URL).accept(MediaType.APPLICATION_JSON);

    LOG.info("URL: {}", FIND_CATEGORY_VALID_ID_URL);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{category:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testFindRecord_validId_recordNotExist() throws Exception {
    when(categoryReadService.findCategory(VALID_NORECORD_ID))
        .thenThrow(new CategoryNotFoundException("Invalid category id"));

    RequestBuilder request = MockMvcRequestBuilders.get(FIND_CATEGORY_VALID_NORECORD_ID_URL)
        .accept(MediaType.APPLICATION_JSON);

    LOG.info("URL: {}", FIND_CATEGORY_VALID_NORECORD_ID_URL);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{errors:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

  }

  @Test
  @Override
  public void testFindRecord_invalidId() throws Exception {
    when(categoryReadService.findCategory(INVALID_ID)).thenThrow(new CategoryNotFoundException("Invalid category id"));

    RequestBuilder request = MockMvcRequestBuilders.get(FIND_CATEGORIES_INVALID_ID_URL)
        .accept(MediaType.APPLICATION_JSON);

    LOG.info("URL: {}", FIND_CATEGORIES_INVALID_ID_URL);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{errors:{}}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testFindRecords_empty() throws Exception {
    CategoriesResponseData responseData = CategoriesResponseData.builder().categories(new ArrayList<CategoryData>())
        .build();
    when(categoryReadService.findCategories()).thenReturn(responseData);

    RequestBuilder request = MockMvcRequestBuilders.get(CATEGORIES_URL).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{categories:[]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

    expectedResponse = "{categories:[0]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(),
        new ArraySizeComparator(JSONCompareMode.LENIENT));
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

    expectedResponse = "{categories:[1]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(),
        new ArraySizeComparator(JSONCompareMode.LENIENT));
  }

  @Test
  @Override
  public void testFindRecords_multiple() throws Exception {
    List<CategoryData> categoryDataSamples = CategoryHelper.getCategoryDataSamples(true);
    CategoriesResponseData responseData = CategoriesResponseData.builder().categories(categoryDataSamples).build();
    when(categoryReadService.findCategories()).thenReturn(responseData);

    RequestBuilder request = MockMvcRequestBuilders.get(CATEGORIES_URL).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{categories:[1, 9999]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(),
        new ArraySizeComparator(JSONCompareMode.LENIENT));
  }

}
