package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@ jakarta.persistence.Table(name = "columns")
public class Column {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private  Long id;
    @jakarta.persistence.Column(nullable = false, unique = true)
    private String name;
    @jakarta.persistence.Column(nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    @JsonIgnore
    private Table table;

    public Column(String name, String type, Table table) {
        this.name = name;
        this.type = type;
        this.table = table;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
