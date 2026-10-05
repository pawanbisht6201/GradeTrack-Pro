package com.gradetrack.mapper;

import com.gradetrack.dto.StudentRequest;
import com.gradetrack.dto.StudentResponse;
import com.gradetrack.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    // Convert request DTO to entity
    public Student toEntity(StudentRequest request) {

        Student student = new Student();

        student.setName(request.getName());
        student.setRollNumber(request.getRollNumber());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setClassName(request.getClassName());
        student.setSemester(request.getSemester());
        student.setAcademicYear(request.getAcademicYear());

        return student;
    }

    // Convert entity to response DTO
    public StudentResponse toResponse(Student student) {

        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setRollNumber(student.getRollNumber());
        response.setEmail(student.getEmail());
        response.setPhone(student.getPhone());
        response.setClassName(student.getClassName());
        response.setSemester(student.getSemester());
        response.setAcademicYear(student.getAcademicYear());
        response.setCreatedAt(student.getCreatedAt());
        response.setUpdatedAt(student.getUpdatedAt());

        return response;
    }

    // Update an existing entity using request data
    public void updateEntity(Student student, StudentRequest request) {

        student.setName(request.getName());
        student.setRollNumber(request.getRollNumber());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setClassName(request.getClassName());
        student.setSemester(request.getSemester());
        student.setAcademicYear(request.getAcademicYear());
    }
}