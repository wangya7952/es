/*
 * Copyright (C) 2018  ChuanBei(ShenZhe) Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
   * 本代码版权归传贝科技（深圳）所有，且受到相关的法律保护。
   * 没有经过版权所有者的书面同意，
   * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.lotgod.es.config;

import org.elasticsearch.client.Client;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * 继承ElasticsearchTemplate,重写父类方法
 * 
 * @author rayping 2019年4月19日 上午10:21:32
 *
 */
public class GwsElasticsearchTemplate extends ElasticsearchTemplate {

	public GwsElasticsearchTemplate(Client client) {
		super(client);
	}

	@Override
	public <T> boolean createIndex(Class<T> clazz) {
		throw new ElasticsearchException("Failed to createIndex for " + clazz.getName());
	}

	@Override
	public boolean createIndex(String indexName) {
		throw new ElasticsearchException("Failed to createIndex for " + indexName);
	}

	@Override
	public <T> boolean putMapping(Class<T> clazz) {
		throw new ElasticsearchException("Failed to putMapping for " + clazz.getName());
	}

	@Override
	public <T> boolean putMapping(Class<T> clazz, Object mapping) {
		throw new ElasticsearchException("Failed to putMapping for " + clazz.getName());
	}

	@Override
	public boolean putMapping(String indexName, String type, Object mapping) {
		throw new ElasticsearchException("Failed to putMapping for " + indexName);
	}

	@Override
	public boolean createIndex(String indexName, Object settings) {
		throw new ElasticsearchException("Failed to createIndex for " + indexName);
	}

	@Override
	public <T> boolean createIndex(Class<T> clazz, Object settings) {
		throw new ElasticsearchException("Failed to createIndex for " + clazz.getName());
	}

}
