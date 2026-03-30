package com.example.demo.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class CreateTableRequest {
    @NotBlank(message = "Table name is required")
    private String name;

    @NotEmpty(message = "At least one column is required")
    @Valid
    private List<ColumnDefinition> columns;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<ColumnDefinition> getColumns() { return columns; }
    public void setColumns(List<ColumnDefinition> columns) { this.columns = columns; }

    public static class ColumnDefinition {
        @NotBlank(message = "Column name is required")
        private String name;

        @NotBlank(message = "Column type is required")
        @Pattern(regexp = "^(string|number|boolean)$",
                message = "Type must be string, number, or boolean")
        private String type;

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    }
}
