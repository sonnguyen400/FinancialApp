package com.sonnguyen.individual.nhs.dao_v2;

public class Sort {
    public String column;
    public String order;
    public enum Order{
        ESC,DESC
    }
    public static Sort of(String column, Order type){
        Sort sort = new Sort();
        sort.column = column;
        if(type == Order.DESC) sort.order="DESC";
        else sort.order="ASC";
        return sort;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
