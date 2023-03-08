package com.gma.challenge.beruang.wallet.util;

import com.gma.challenge.beruang.category.model.Category;
import com.gma.challenge.beruang.generated.dto.CategoryData;
import com.gma.challenge.beruang.generated.dto.NewWalletRequestData;
import com.gma.challenge.beruang.generated.dto.UpdateWalletRequestData;
import com.gma.challenge.beruang.generated.dto.WalletData;
import com.gma.challenge.beruang.wallet.model.Wallet;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gma.challenge.beruang.category.util.Mapper.toCategory;
import static com.gma.challenge.beruang.category.util.Mapper.toCategoryData;

public class Mapper {
    public static WalletData toWalletData(Wallet wallet) {
        WalletData walletData = new WalletData();
        BeanUtils.copyProperties(wallet, walletData);

        List<CategoryData> categoryDatas =
                wallet.getCategories().stream()
                        .map(category -> toCategoryData(category))
                        .collect(Collectors.toList());

        walletData.setCategories(categoryDatas);

        return walletData;
    }

    public static Wallet toWallet(NewWalletRequestData requestData) {
        Wallet wallet = new Wallet();
        wallet.setName(requestData.getName());
        wallet.setDefaultCurrencyCode(requestData.getDefaultCurrencyCode());

        Set<Category> categories =
                requestData.getCategoryIds().stream()
                        .map(categoryId -> toCategory(categoryId))
                        .collect(Collectors.toSet());

        wallet.setCategories(categories);

        if (requestData.getInitialBalanceAmount() == null) {
            wallet.setInitialBalanceAmount(BigDecimal.ZERO);
        } else {
            wallet.setInitialBalanceAmount(requestData.getInitialBalanceAmount());
        }

        return wallet;
    }

    public static Wallet updateWallet(Wallet wallet, UpdateWalletRequestData requestData) {
        if (requestData.getName() != null && !requestData.getName().isBlank()) {
            wallet.setName(requestData.getName());
        }

        if (requestData.getDefaultCurrencyCode() != null
                && !requestData.getDefaultCurrencyCode().isBlank()) {
            wallet.setDefaultCurrencyCode(requestData.getDefaultCurrencyCode());
        }

        if (requestData.getInitialBalanceAmount() != null) {
            wallet.setInitialBalanceAmount(requestData.getInitialBalanceAmount());
        }

        return wallet;
    }
}
