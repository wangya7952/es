/*
 * Copyright (C) 2016  SHENZHEN ChuanBei Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归传贝(深圳)所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.lotgod.utils.elasticsearch;

import com.google.common.collect.Lists;
import com.lotgod.utils.elasticsearch.BaseEsQuery;
import com.lotgod.utils.elasticsearch.annotation.EsBindAttrField;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * es工具类
 *
 * @version
 * @author leiyongping 2017年4月21日 下午3:46:20
 *
 */
public final class EsUtils {
	/**
	 *
	 * 快速查询is，in 用and 条件拼接
	 *
	 * @author leiyongping 2017年4月21日
	 * @param searchObj
	 * @param clazz
	 * @return
	 */
	public static <T> CriteriaQuery fastQuery(BaseEsQuery searchObj, Pageable pageable, Sort.Direction sortType,
											  String sortFieldName) {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(searchObj);
		Criteria c = null;

		for (PropertyDescriptor pd : pds) {
			if ("class".equals(pd.getName())) {
				continue;
			}
			Method readMethod = pd.getReadMethod();
			try {
				Object obj = readMethod.invoke(searchObj);
				if (obj == null) {
					continue;
				}
				Annotation fieldProp = getBindFieldName(searchObj, pd.getName());

				if (fieldProp instanceof EsBindAttrField || fieldProp == null) {
					String bindAttrName = null;
					EsWhere where = null;
					if (fieldProp == null) {

					} else {
						bindAttrName = ((EsBindAttrField) fieldProp).fieldName();
						where = ((EsBindAttrField) fieldProp).where();
					}

					if (bindAttrName == null) {
						// 查询字段名称默认等于属性名称
						bindAttrName = pd.getName();
					}

					if ("page".equals(bindAttrName) || "pageSize".equals(bindAttrName)
							|| "sortDirct".equals(bindAttrName) || "sortFieldName".equals(bindAttrName)) {
						continue;
					}

					if (where == null) {
						// where字段默认为空，则默认为相等
						where = EsWhere.equal;
					}

					switch (where) {
					case equal:
						if (c == null) {
							c = new Criteria(bindAttrName).is(obj);
						} else {
							c = c.and(new Criteria(bindAttrName).is(obj));
						}
						break;
					case in:
						if (obj instanceof java.util.Collection<?>) {
							List<?> value = (List<?>) obj;
							if (value.size() == 0) {
								// 防止生成LIST时，没有传入值，而查询条件会做全查处理，此处做特殊处理返回空条件
								((List<?>) obj).add(null);
							}
							if (value.size() > 20) {
								Set<Object> set = new HashSet<Object>(value.size());
								// 如果in超过20个要去重处理
								set.addAll(value);
								value = new ArrayList<Object>(set);
							}

							if (c == null) {
								c = new Criteria(bindAttrName).in(value);
							} else {
								c = c.and(new Criteria(bindAttrName).in(value));
							}
						}
						break;
					case like:
						if (c == null) {
							c = new Criteria(bindAttrName).contains((String) obj);
						} else {
							c = c.and(new Criteria(bindAttrName).contains((String) obj));
						}
						break;
					case greaterThan:
						if (c == null) {
							c = new Criteria(bindAttrName).greaterThan(obj);
						} else {
							c = c.and(new Criteria(bindAttrName).greaterThan(obj));
						}
						break;
					case greaterThanOrEqualTo:
						if (c == null) {
							c = new Criteria(bindAttrName).greaterThanEqual(obj);
						} else {
							c = c.and(new Criteria(bindAttrName).greaterThanEqual(obj));
						}
						break;
					case lessThan:
						if (c == null) {
							c = new Criteria(bindAttrName).lessThan(obj);
						} else {
							c = c.and(new Criteria(bindAttrName).lessThan(obj));
						}
						break;
					case lessThanOrEqualTo:
						if (c == null) {
							c = new Criteria(bindAttrName).lessThanEqual(obj);
						} else {
							c = c.and(new Criteria(bindAttrName).lessThanEqual(obj));
						}
						break;
					case notEqual:
						if (c == null) {
							c = new Criteria(bindAttrName).notIn(obj);
						} else {
							c = c.and(new Criteria(bindAttrName).notIn(obj));
						}
						break;
					case notin:
						if (obj instanceof java.util.Collection<?>) {
							List<?> value = (List<?>) obj;
							if (value.size() == 0) {
								// 防止生成LIST时，没有传入值，而查询条件会做全查处理，此处做特殊处理返回空条件
								((List<?>) obj).add(null);
							}
							if (value.size() > 20) {
								Set<Object> set = new HashSet<Object>(value.size());
								// 如果in超过20个要去重处理
								set.addAll(value);
								value = Lists.newArrayList(set);
							}

							if (c == null) {
								c = new Criteria(bindAttrName).notIn(value);
							} else {
								c = c.and(new Criteria(bindAttrName).notIn(value));
							}
						}
						break;
					default:
						break;

					}

				} else {
					continue;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO 这是系统自动生成描述，请在此补完后续代码
			}
		}
		if (c != null) {

			CriteriaQuery criteriaQuery = new CriteriaQuery(c);
			if (pageable != null) {
				criteriaQuery.setPageable(pageable);
			}
			if (sortFieldName != null && sortType != null) {
				Sort sort = Sort.by(new Sort.Order(sortType, sortFieldName));
				criteriaQuery.addSort(sort);
			}

			return criteriaQuery;
		}
		return null;
	}

	/**
	 *
	 * 获取绑定字段属性值
	 *
	 * @author leiyongping 2016年4月16日
	 * @param PropertyName
	 * @return
	 */
	public static Annotation getBindFieldName(BaseEsQuery query, String PropertyName) {
		try {
			Field field = query.getClass().getDeclaredField(PropertyName);
			Annotation anno = field.getAnnotation(EsBindAttrField.class);
			if (anno != null) {
				return (anno);
			}
		} catch (SecurityException e) {
		} catch (NoSuchFieldException e) {
			return null;
		}
		return null;
	}
}
