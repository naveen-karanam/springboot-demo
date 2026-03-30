package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "tables")
public class Table {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private   Long id;

    @jakarta.persistence.Column(nullable = false, unique = true)
    private String name;
    @jakarta.persistence.Column(nullable = false)
    private LocalDateTime createdAt;

    public Table(String name) {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    public Table() {
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Column> columns = new ArrayList<>();

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Row> rows = new ArrayList<>();


}
