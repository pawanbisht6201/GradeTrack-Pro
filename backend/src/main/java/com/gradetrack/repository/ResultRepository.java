package com.gradetrack.repository;

import com.gradetrack.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {

    // Find result by student ID
    Optional<Result> findByStudentId(Long studentId);

    // Check whether a student already has a result
    boolean existsByStudentId(Long studentId);

    // Get all results sorted by percentage - highest first
    List<Result> findAllByOrderByPercentageDesc();
}