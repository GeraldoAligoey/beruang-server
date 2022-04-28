package com.gma.challenge.beruang.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryRestControllerTest {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryRestControllerTest.class);
  private static final String REST_CATEGORY = "/rest/category/";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Sql("classpath:test_category.sql")
  public void testFindAll() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get(REST_CATEGORY).accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
    LOG.info("result: {}", result.getResponse().getContentAsString()); 
  }
}
