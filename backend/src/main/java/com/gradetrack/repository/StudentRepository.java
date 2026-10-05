package com.gradetrack.repository;

import com.gradetrack.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Check duplicate roll number while creating
    boolean existsByRollNumber(String rollNumber);

    // Check duplicate roll number while updating
    boolean existsByRollNumberAndIdNot(String rollNumber, Long id);

    // Check duplicate email while creating
    boolean existsByEmail(String email);

    // Check duplicate email while updating
    boolean existsByEmailAndIdNot(String email, Long id);
}