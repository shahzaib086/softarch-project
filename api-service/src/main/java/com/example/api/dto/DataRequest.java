package com.example.api.dto;

public class DataRequest {
    private String filter;
    private int page;
    private String des;
    private String name;

    // Getter and Setter for filter
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    // Getter and Setter for page
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    // Getter and Setter for des
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
