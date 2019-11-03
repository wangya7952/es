package com.lotgod.es.repositories;


import com.lotgod.es.entity.BankSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author lwg linweiguang@wuzhenpay.com
 * @date 2019/3/11 19:01
 * @copyright Copyright (c) 2019 wuzhenpay Inc. All Rights Reserved.
 * @description
 */
public interface BankSearchRepository extends ElasticsearchRepository<BankSearch, Integer> {

}
