package com.lotgod.common.model.page;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lwg linweiguang@wuzhenpay.com
 * @date 2019/1/7 17:47
 * @copyright Copyright (c) 2018 wuzhenpay Inc. All Rights Reserved.
 * @description
 */
@Data
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 8470697978259453214L;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页显示多少条
     */
    private int size;

    //查询
    /**
     * 总记录数
     */
    private long total;

    /**
     * 本页的数据列表
     */
    private List<T> rows;

    public PageBean() {
    }

    /**
     * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
     *
     * @param page
     * @param size
     * @param total
     * @param rows
     */
    public PageBean(int page, int size, long total, List<T> rows) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.rows = rows;
    }

    /**
     *
     * @param page
     * @param size
     * @param sourceList  需要统计的数据列表
     * @param rows
     */
    public PageBean(int page, int size, List sourceList, List<T> rows) {
        this.page = page;
        this.size = size;
        this.total = new PageInfo<>(sourceList).getTotal();
        this.rows = rows;
    }

}
