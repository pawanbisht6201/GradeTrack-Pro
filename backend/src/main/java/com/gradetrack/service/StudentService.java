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

    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        return studentMapper.toResponse(student);
    }

    public StudentResponse saveStudent(StudentRequest request) {

        if (studentRepository.existsByRollNumber(
                request.getRollNumber())) {

            throw new DuplicateResourceException(
                    "Roll number already exists: "
                            + request.getRollNumber()
            );
        }

        Student student = studentMapper.toEntity(request);

        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    public StudentResponse updateStudent(
            Long id,
            StudentRequest request) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        if (studentRepository.existsByRollNumberAndIdNot(
                request.getRollNumber(),
                id)) {

            throw new DuplicateResourceException(
                    "Roll number already exists: "
                            + request.getRollNumber()
            );
        }

        studentMapper.updateEntity(
                existingStudent,
                request
        );

        Student updatedStudent =
                studentRepository.save(existingStudent);

        return studentMapper.toResponse(updatedStudent);
    }

    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        studentRepository.delete(student);
    }
}