package com.gradetrack.service;

import com.gradetrack.dto.StudentRequest;
import com.gradetrack.dto.StudentResponse;
import com.gradetrack.entity.Student;
import com.gradetrack.exception.DuplicateResourceException;
import com.gradetrack.exception.ResourceNotFoundException;
import com.gradetrack.mapper.StudentMapper;
import com.gradetrack.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(
            StudentRepository studentRepository,
            StudentMapper studentMapper) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    // ==========================================
    // GET ALL STUDENTS
    // ==========================================

    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }


    // ==========================================
    // GET STUDENT BY ID
    // ==========================================

    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        return studentMapper.toResponse(student);
    }


    // ==========================================
    // CREATE STUDENT
    // ==========================================

    public StudentResponse saveStudent(StudentRequest request) {

        // Check duplicate roll number
        if (studentRepository.existsByRollNumber(
                request.getRollNumber())) {

            throw new DuplicateResourceException(
                    "Roll number already exists: "
                            + request.getRollNumber()
            );
        }

        // Check duplicate email
        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && studentRepository.existsByEmail(
                        request.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists: "
                            + request.getEmail()
            );
        }

        // Convert DTO to Entity
        Student student = studentMapper.toEntity(request);

        // Save student
        Student savedStudent =
                studentRepository.save(student);

        // Convert Entity to Response DTO
        return studentMapper.toResponse(savedStudent);
    }


    // ==========================================
    // UPDATE STUDENT
    // ==========================================

    public StudentResponse updateStudent(
            Long id,
            StudentRequest request) {

        // Find existing student
        Student existingStudent =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found with id: "
                                                + id
                                )
                        );

        // Check duplicate roll number
        if (studentRepository.existsByRollNumberAndIdNot(
                request.getRollNumber(),
                id)) {

            throw new DuplicateResourceException(
                    "Roll number already exists: "
                            + request.getRollNumber()
            );
        }

        // Check duplicate email
        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && studentRepository.existsByEmailAndIdNot(
                        request.getEmail(),
                        id)) {

            throw new DuplicateResourceException(
                    "Email already exists: "
                            + request.getEmail()
            );
        }

        // Update entity
        studentMapper.updateEntity(
                existingStudent,
                request
        );

        // Save updated student
        Student updatedStudent =
                studentRepository.save(existingStudent);

        // Return response
        return studentMapper.toResponse(updatedStudent);
    }


    // ==========================================
    // DELETE STUDENT
    // ==========================================

    public void deleteStudent(Long id) {

        Student student =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found with id: "
                                                + id
                                )
                        );

        studentRepository.delete(student);
    }
}