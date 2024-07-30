package com.sonnguyen.individual.nhs.dao.core;

import java.util.List;

public class Pagination {
    private Integer page;
    private Integer size;
    private List<Sort> sorts;
    public Pagination(int page, int size, Sort ... sorts) {
        this.page = page;
        this.size = size;
        this.sorts=List.of(sorts);
    }
    public Pagination(int page, int size) {
        this.page=page;
    }
    public static Pagination of(int page,int size, Sort ... sorts) {
        return new Pagination(page,size,sorts);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }
}
