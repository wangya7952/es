package com.lotgod.es.common.model.page;

import com.github.pagehelper.Page;

import java.util.List;

public class PageList {
    private int pageNum; // 当前页数
    private int pageSize; // 翻页时每页条数
    private long total; // 总条数
    private List list; // 列表
    private int size; // 当前页条数

    public PageList(List list) {
        if (list instanceof Page) {
            // 转换Page对象
            Page page = (Page)list;

            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.list = page;
            this.size = page.size();
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
