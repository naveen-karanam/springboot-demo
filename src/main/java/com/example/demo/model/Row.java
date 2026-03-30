package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "rows")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.persistence.Column(columnDefinition = "TEXT")
    private String data;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    @JsonIgnore
    private Table table;

    public Row() {
        this.createdAt = LocalDateTime.now();
    }

    public Row(String data, Table table) {
        this.data = data;
        this.table = table;
        this.createdAt = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Table getTable() { return table; }
    public void setTable(Table table) { this.table = table; }
}