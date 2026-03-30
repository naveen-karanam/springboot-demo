package com.example.demo.model.dto;

import java.util.Map;

public class InsertRowRequest {
    private Map<String, Object> data;

    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }
}