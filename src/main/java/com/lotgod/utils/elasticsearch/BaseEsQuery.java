/*
 * Copyright (C) 2016  SHENZHEN ChuanBei Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归传贝(深圳)所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.lotgod.utils.elasticsearch;

import com.lotgod.utils.elasticsearch.annotation.EsBindOperator;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

/**
 * query基类，注意分库分表的sharding-col是不能用使用In查询的，路由不了。
 *
 * @version
 * @author leiyongping 2016年4月16日 下午3:48:02
 *
 */
public abstract class BaseEsQuery {

	@EsBindOperator
	private Integer page;
	@EsBindOperator
	private Integer pageSize;
	@EsBindOperator
	private Sort.Direction sortDirct;
	// ES实体类属性名称
	@EsBindOperator
	private String sortFieldName;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Sort.Direction getSortDirct() {
		return sortDirct;
	}

	public void setSortDirct(Sort.Direction sortDirct) {
		this.sortDirct = sortDirct;
	}

	public String getSortFieldName() {
		return sortFieldName;
	}

	public void setSortFieldName(String sortFieldName) {
		this.sortFieldName = sortFieldName;
	}

	/**
	 *
	 * 创建一个CriteriaQuery 传入ES模板中
	 *
	 * @author leiyongping 2017年4月21日
	 * @return
	 */
	public CriteriaQuery buildQuery() {
		PageRequest pr = null;
		if (page != null && pageSize != null) {
			pr = PageRequest.of(page, pageSize);
		}
		return EsUtils.fastQuery(this, pr, sortDirct, sortFieldName);
	}

	/**
	 *
	 * 拷贝其它对象属性
	 *
	 * @author leiyongping 2017年4月21日
	 * @param orign
	 */
	public BaseEsQuery copyPropValue(Object orign) {
		try {
			PropertyUtils.copyProperties(this, orign);
		} catch (Exception e) {
		}
		return this;
	}

}
