package com.example.demo.repository;

import com.example.demo.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
    Optional<Table> findByName(String name);
    boolean existsByName(String name);
}
