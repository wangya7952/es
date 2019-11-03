/*
 * Copyright (C) 2016  SHENZHEN ChuanBei Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归传贝(深圳)所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.lotgod.utils.elasticsearch.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 查询绑定实体类
 *
 * @version
 * @author leiyongping 2016年4月16日 下午6:18:03
 * 
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EsBindEntity {
	public abstract Class<?> entityClass();
}
