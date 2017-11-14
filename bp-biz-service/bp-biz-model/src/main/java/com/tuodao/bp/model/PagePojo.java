package com.tuodao.bp.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * 分页请求信息
 *
 * @author 摇光 [NO.0146]
 * @since 2016年09月23日 9:29
 */
public class PagePojo extends BasePojo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 每页记录数(默认每页20条)
     */
    private int pageSize = 20;

    /**
     * 当前页码(默认第一页)
     */
    private int currentPage = 1;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("pageSize", pageSize)
                .add("currentPage", currentPage)
                .toString();
    }
}
