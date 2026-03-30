package com.example.demo.repository;

import com.example.demo.model.Row;
import com.example.demo.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RowRepository extends JpaRepository<Row, Long> {
    List<Row> findByTableOrderByIdAsc(Table table);
}