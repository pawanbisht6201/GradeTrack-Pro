package com.gradetrack.repository;

import com.gradetrack.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByRollNumber(String rollNumber);

    boolean existsByRollNumberAndIdNot(String rollNumber, Long id);
}