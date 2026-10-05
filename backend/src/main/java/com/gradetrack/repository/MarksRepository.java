package com.gradetrack.repository;

import com.gradetrack.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    Optional<Marks> findByResultId(Long resultId);

    boolean existsByResultId(Long resultId);
}