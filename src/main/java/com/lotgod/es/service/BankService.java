package com.lotgod.es.service;

import com.lotgod.es.entity.BankSearch;

import java.util.List;

/**
 * @author lwg linweiguang@wuzhenpay.com
 * @date 2019/1/7 17:23
 * @copyright Copyright (c) 2018 wuzhenpay Inc. All Rights Reserved.
 * @description
 */
public interface BankService {

    public void batchInsertBankSearch(List<BankSearch> bankSearchList) ;
}