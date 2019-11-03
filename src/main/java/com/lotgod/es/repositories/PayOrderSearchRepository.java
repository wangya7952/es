package com.lotgod.es.repositories;

import com.lotgod.es.entity.PayOrderSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PayOrderSearchRepository extends ElasticsearchRepository<PayOrderSearch, String> {

}
