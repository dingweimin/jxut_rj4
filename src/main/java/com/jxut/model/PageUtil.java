package com.jxut.model;

/**
 * @Author: WeiMin
 * @Date: 2022/5/31 9:10
 */
public class PageUtil {
    private int pageSize;//页数
    private int totalSize;//总条数
    private int totalPage;//总页数
    private int currentPage;//当前页

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        if (this.totalSize % this.pageSize==0) {
            this.totalPage = this.totalSize/ this.pageSize;
        } else {
            this.totalPage = this.totalSize /this.pageSize + 1;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
