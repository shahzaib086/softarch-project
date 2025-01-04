package com.example.processor.repository;

import com.example.processor.model.ProcessedData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedDataRepository extends JpaRepository<ProcessedData, Long> {}