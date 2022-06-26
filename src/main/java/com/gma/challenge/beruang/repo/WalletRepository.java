package com.gma.challenge.beruang.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gma.challenge.beruang.domain.Wallet;

@Repository
@Transactional
public interface WalletRepository extends JpaRepository<Wallet, Long> {

  @Query(value = "select * from wallet where wallet.id in (select w_c.wallet_id from wallet_categories w_c where w_c.categories_id in :categoryIds)", nativeQuery = true)
  public List<Wallet> findByCategoryIds(List<Long> categoryIds);

  @Modifying
  @Query("update Wallet w set w.defaultWallet = false where w.defaultWallet = true")
  public void setAllDefaultFalse();

}
