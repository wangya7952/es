package com.lotgod.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author lwg linweiguang@wuzhenpay.com
 * @date 2019/4/25 17:24
 * @copyright Copyright (c) 2019 wuzhenpay Inc. All Rights Reserved.
 * @description 银行分行索引
 */
@Data
@Document(indexName = "bank", type = "bank", createIndex = false)
public class BankSearch implements Serializable {

    private static final long serialVersionUID = -4290857154816909604L;

    @Field(type = FieldType.Integer)
    @Id
    private Integer id;

    /**
     * 银行名称
     */
    @Field(index = true, type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String bankName;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Integer)
    private Integer createTime;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Integer)
    private Integer updateTime;

    /**
     * 版本号
     */
    @Field(type = FieldType.Integer)
    private Integer version;

    /**
     * 创建人账户id
     */
    @Field(type = FieldType.Integer)
    private Integer createAccountId;

    /**
     * 更新人账户id
     */
    @Field(type = FieldType.Integer)
    private Integer updateAccountId;

    /**
     * 银行图标
     */
    @Field(type = FieldType.Keyword)
    private String bankIcon;

}
