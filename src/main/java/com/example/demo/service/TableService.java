package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.dto.*;
import com.example.demo.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TableService {
    private final TableRepository tableRepository;
    private final ColumnRepository columnRepository;
    private final RowRepository rowRepository;
    private final ObjectMapper objectMapper;

    public TableService(TableRepository tableRepository,
                        ColumnRepository columnRepository,
                        RowRepository rowRepository) {
        this.tableRepository = tableRepository;
        this.columnRepository = columnRepository;
        this.rowRepository = rowRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    public Long createTable(CreateTableRequest request) {
        // Check if table name already exists
        if (tableRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Table name already exists: " + request.getName());
        }

        // Create table
        Table table = new Table(request.getName());
        table = tableRepository.save(table);

        // Create columns
        Set<String> columnNames = new HashSet<>();
        for (CreateTableRequest.ColumnDefinition colDef : request.getColumns()) {
            if (!columnNames.add(colDef.getName())) {
                throw new IllegalArgumentException("Duplicate column name: " + colDef.getName());
            }

            Column column = new Column(colDef.getName(), colDef.getType(), table);
            columnRepository.save(column);
            table.getColumns().add(column);
        }

        return table.getId();
    }

    @Transactional
    public RowResponse insertRow(Long tableId, InsertRowRequest request) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Table not found: " + tableId));

        // Validate data against column definitions
        Map<String, Object> data = request.getData();
        Map<String, Column> columnMap = new HashMap<>();

        for (Column column : table.getColumns()) {
            columnMap.put(column.getName(), column);
        }

        // Check for extra fields
        for (String field : data.keySet()) {
            if (!columnMap.containsKey(field)) {
                throw new IllegalArgumentException("Unknown column: " + field);
            }
        }

        // Validate each field
        for (Map.Entry<String, Column> entry : columnMap.entrySet()) {
            String columnName = entry.getKey();
            Column column = entry.getValue();
            Object value = data.get(columnName);

            if (value == null) {
                throw new IllegalArgumentException("Missing required column: " + columnName);
            }

            validateType(value, column.getType(), columnName);
        }

        // Save row as JSON
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            Row row = new Row(jsonData, table);
            row = rowRepository.save(row);

            return new RowResponse(row.getId(), data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize row data", e);
        }
    }

    public List<RowResponse> getRows(Long tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Table not found: " + tableId));

        List<Row> rows = rowRepository.findByTableOrderByIdAsc(table);
        List<RowResponse> responses = new ArrayList<>();

        for (Row row : rows) {
            try {
                Map<String, Object> data = objectMapper.readValue(row.getData(),
                        objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class));
                responses.add(new RowResponse(row.getId(), data));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to deserialize row data", e);
            }
        }

        return responses;
    }

    private void validateType(Object value, String expectedType, String columnName) {
        switch (expectedType) {
            case "string":
                if (!(value instanceof String)) {
                    throw new IllegalArgumentException(
                            "Column " + columnName + " expects string, got: " + value.getClass().getSimpleName());
                }
                break;
            case "number":
                if (!(value instanceof Number)) {
                    throw new IllegalArgumentException(
                            "Column " + columnName + " expects number, got: " + value.getClass().getSimpleName());
                }
                break;
            case "boolean":
                if (!(value instanceof Boolean)) {
                    throw new IllegalArgumentException(
                            "Column " + columnName + " expects boolean, got: " + value.getClass().getSimpleName());
                }
                break;
        }
    }
}