package com.example.demo.model.dto;

import java.util.Map;

public class RowResponse {
    private Long id;
    private Map<String, Object> data;

    public RowResponse(Long id, Map<String, Object> data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }
}
