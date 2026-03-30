package com.example.demo.controller;

import com.example.demo.model.dto.*;
import com.example.demo.service.TableService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createTable(@Valid @RequestBody CreateTableRequest request) {
        Long tableId = tableService.createTable(request);
        return ResponseEntity.ok(Map.of("id", tableId));
    }

    @PostMapping("/{table_id}/rows")
    public ResponseEntity<RowResponse> insertRow(@PathVariable("table_id") Long tableId,
                                                 @RequestBody InsertRowRequest request) {
        RowResponse row = tableService.insertRow(tableId, request);
        return ResponseEntity.ok(row);
    }

    @GetMapping("/{table_id}/rows")
    public ResponseEntity<List<RowResponse>> getRows(@PathVariable("table_id") Long tableId) {
        List<RowResponse> rows = tableService.getRows(tableId);
        return ResponseEntity.ok(rows);
    }
}
