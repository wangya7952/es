package com.lotgod.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lotgod.es.entity.BankBranchSearch;
import com.lotgod.es.entity.BankSearch;
import com.lotgod.es.entity.PayOrderSearch;
import com.lotgod.es.service.BankBranchService;
import com.lotgod.es.service.BankService;
import org.assertj.core.util.Lists;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 * es 批量导入测试（必须学好）
 * 2019-09-10
 * wnagyunan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBulkTests {
	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Autowired
	private BankBranchService bankBranchService;


	@Autowired
	private BankService bankService;


	@Test
	public void batchInsertPayOrderSearch() {
		List<PayOrderSearch> list = this.getPayOrderSearchList();
		this.bulkSave(list);

	}

	@Test
	public void batchInsertBankBranchSearch() {
		List<BankBranchSearch> list = this.getBankBranchSearchList();
		bankBranchService.batchInsertBankSearch(list);

	}

	@Test
	public void batchInsertBankSearch() {
		List<BankSearch> list = this.getBankSearchList();
		bankService.batchInsertBankSearch(list);
	}

	private List<PayOrderSearch> getPayOrderSearchList()  {
		List<PayOrderSearch> list = new ArrayList<PayOrderSearch>();
		PayOrderSearch payOrderSearch =null;
		try {
			String filePath = "F:\\es_data\\order_share_profit.json";
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String json = null;
			int count = 0;
			boolean b = false;
			while ((json = br.readLine()) != null) {
				payOrderSearch = JSONObject.parseObject(json,PayOrderSearch.class);
				list.add(payOrderSearch);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return  list;
	}

	private List<BankBranchSearch> getBankBranchSearchList()  {
		List<BankBranchSearch> list = new ArrayList<BankBranchSearch>();
		BankBranchSearch bankBranchSearch =null;
		try {
			String filePath = "D:\\传贝资料\\es数据备份\\bank_branch.json";
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String json = null;
			int count = 0;
			boolean b = false;
			while ((json = br.readLine()) != null) {
				bankBranchSearch = JSONObject.parseObject(json,BankBranchSearch.class);
				list.add(bankBranchSearch);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return  list;
	}

	private List<BankSearch> getBankSearchList()  {
		List<BankSearch> list = new ArrayList<BankSearch>();
		BankSearch bankSearch =null;
		try {
			String filePath = "D:\\传贝资料\\es数据备份\\bank.json";
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String json = null;
			int count = 0;
			boolean b = false;
			while ((json = br.readLine()) != null) {
				bankSearch = JSONObject.parseObject(json,BankSearch.class);
				list.add(bankSearch);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return  list;
	}
	public boolean bulkSave(List<PayOrderSearch> orderInfoEsSearchList) {
		if (null == orderInfoEsSearchList || orderInfoEsSearchList.size() <= 0) {
			return false;
		}

		List<IndexQuery> indexQueries = Lists.newArrayList();
		ElasticsearchPersistentEntity<?> persistentEntity = esTemplate.getPersistentEntityFor(PayOrderSearch.class);
		String indices = persistentEntity.getIndexName();
		String type = persistentEntity.getIndexType();

		for (PayOrderSearch orderInfoEsSearch : orderInfoEsSearchList) {
			IndexQuery indexQuery = new IndexQueryBuilder()
					.withId(orderInfoEsSearch.getPayNo()) // 主键id
					.withIndexName(indices) // 索引名称
					.withType(type) // 索引类型
					.withSource(JSON.toJSONString(orderInfoEsSearch)) // 新增数据源，转为json格式
					.build();
			indexQueries.add(indexQuery);
		}

		if (indexQueries.size() > 0) {
			esTemplate.bulkIndex(indexQueries);
		}
		return true;
	}
}
