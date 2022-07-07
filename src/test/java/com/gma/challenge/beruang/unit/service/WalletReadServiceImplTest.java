package com.gma.challenge.beruang.unit.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gma.challenge.beruang.common.ReadServiceTest;
import com.gma.challenge.beruang.common.helper.WalletHelper;
import com.gma.challenge.beruang.data.WalletResponseData;
import com.gma.challenge.beruang.data.WalletsResponseData;
import com.gma.challenge.beruang.domain.Wallet;
import com.gma.challenge.beruang.exception.WalletNotFoundException;
import com.gma.challenge.beruang.repo.WalletRepository;
import com.gma.challenge.beruang.service.WalletReadServiceImpl;

@ExtendWith(MockitoExtension.class)
public class WalletReadServiceImplTest implements ReadServiceTest {

  private static final Long VALID_NORECORD_ID = Long.valueOf(5000);
  private static final Long VALID_ID = Long.valueOf(1);
  private static final Long INVALID_ID = Long.valueOf(-1);

  @InjectMocks
  private WalletReadServiceImpl SUT;

  @Mock
  private WalletRepository walletRepository; 

  @Test
  @Override
  public void testFind_empty() {
    when(walletRepository.findAll()).thenReturn(new ArrayList<>());

    WalletsResponseData responseData = SUT.findWallets();
    assertNotNull(responseData);
    assertNotNull(responseData.getWallets());
    assertTrue(responseData.getWallets().isEmpty());
  }

  @Test
  @Override
  public void testFind_single() {
    Wallet wallet = WalletHelper.getWalletSample();
    List<Wallet> wallets = new ArrayList<>();
    wallets.add(wallet);
    
    when(walletRepository.findAll()).thenReturn(wallets);

    WalletsResponseData responseData = SUT.findWallets();
    assertNotNull(responseData);
    assertNotNull(responseData.getWallets());
    assertTrue(responseData.getWallets().size() == 1);
  }

  @Test
  @Override
  public void testFind_multiple() {
    when(walletRepository.findAll()).thenReturn(WalletHelper.getWalletSamples());

    WalletsResponseData responseData = SUT.findWallets();

    assertNotNull(responseData);
    assertNotNull(responseData.getWallets());
    assertTrue(responseData.getWallets().size() > 1);
  }

  @Test
  @Override
  public void testFind_validId_recordExist() {
    Optional<Wallet> optional = Optional.of(WalletHelper.getWalletSample());

    when(walletRepository.findById(VALID_ID)).thenReturn(optional);

    WalletResponseData responseData = SUT.findWallet(VALID_ID);

    assertNotNull(responseData.getWallet());
    assertNotNull(responseData.getWallet().getId());
    assertNotNull(responseData.getWallet().getName());
    assertNotNull(responseData.getWallet().getDefaultWallet());
    assertNotNull(responseData.getWallet().getDefaultCurrencyCode());
    assertNotNull(responseData.getWallet().getInitialBalanceAmount());
    assertNotNull(responseData.getWallet().getCategories());
  }

  @Test
  @Override
  public void testFind_validId_recordNotExist() {
    when(walletRepository.findById(VALID_NORECORD_ID)).thenReturn(Optional.empty());

    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(VALID_NORECORD_ID));
  }

  @Test
  @Override
  public void testFind_invalidId() {
    when(walletRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

    assertThrows(WalletNotFoundException.class, () -> SUT.findWallet(INVALID_ID));
  }
  
}
