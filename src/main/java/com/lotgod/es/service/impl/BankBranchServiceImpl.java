package com.lotgod.es.service.impl;

import com.lotgod.es.entity.BankBranchSearch;
import com.lotgod.es.entity.BankSearch;
import com.lotgod.es.repositories.BankBranchSearchRepository;
import com.lotgod.es.service.BankBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankBranchServiceImpl implements BankBranchService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private BankBranchSearchRepository bankBranchSearchRepository;
    @Override
    public void batchInsertBankSearch(List<BankBranchSearch> bankBranchSearchList) {
        try {
            bankBranchSearchRepository.saveAll(bankBranchSearchList);
            // 立即实现内存->文件系统缓存， 从而使文档可以立即被搜索到
            elasticsearchTemplate.refresh(BankBranchSearch.class);
        } catch (Exception e) {
            e.printStackTrace();
//            GwsLogger.error(e,"执行批量插入ES订单数据发生异常，原因:{}", e.getMessage());
        }
    }
}
