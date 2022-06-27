package com.gma.challenge.beruang.unit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.gma.challenge.beruang.common.helper.WalletHelper;
import com.gma.challenge.beruang.controller.WalletController;
import com.gma.challenge.beruang.service.BudgetReadService;
import com.gma.challenge.beruang.service.BudgetWriteService;
import com.gma.challenge.beruang.service.TransactionReadService;
import com.gma.challenge.beruang.service.TransactionWriteService;
import com.gma.challenge.beruang.service.WalletReadService;
import com.gma.challenge.beruang.service.WalletWriteService;

@WebMvcTest(WalletController.class)
public class WalletControllerTest implements ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WalletReadService walletReadService;

  @MockBean
  private WalletWriteService walletWriteService;

  @MockBean
  private BudgetReadService budgetReadService;

  @MockBean
  private BudgetWriteService budgetWriteService;

  @MockBean
  private TransactionReadService transactionReadService;

  @MockBean
  private TransactionWriteService transactionWriteService;

  private static final Logger LOG = LoggerFactory.getLogger(WalletControllerTest.class);

  private static final String WALLETS_URL = "/wallets";

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

  @Test
  @Override
  public void testFindRecords_empty() throws Exception {
    when(walletReadService.findWallets()).thenReturn(WalletHelper.getWalletsResponseDataEmptySample());

    RequestBuilder request = MockMvcRequestBuilders.get(WALLETS_URL).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{wallets:[]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testFindRecords_single() throws Exception {
    when(walletReadService.findWallets()).thenReturn(WalletHelper.getWalletsResponseDataSingleSample());

    RequestBuilder request = MockMvcRequestBuilders.get(WALLETS_URL).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{wallets:[{id:1,categories:[{},{},{},{},{}]}]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  @Override
  public void testFindRecords_multiple() throws Exception {
    when(walletReadService.findWallets()).thenReturn(WalletHelper.getWalletsResponseDataSample());

    RequestBuilder request = MockMvcRequestBuilders.get(WALLETS_URL).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    LOG.info(result.getResponse().getContentAsString());

    String expectedResponse = "{wallets:[{id:1,categories:[{},{},{},{},{}]},{id:2,categories:[{},{}]}]}";
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }
}
