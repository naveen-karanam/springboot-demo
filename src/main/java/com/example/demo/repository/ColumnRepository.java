package com.example.demo.repository;


import com.example.demo.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Long> {
    boolean existsByTableIdAndName(Long tableId, String name);
}
