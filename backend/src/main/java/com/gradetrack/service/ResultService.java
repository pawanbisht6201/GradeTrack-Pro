package com.gradetrack.service;

import com.gradetrack.dto.ResultRequest;
import com.gradetrack.dto.ResultResponse;
import com.gradetrack.entity.Marks;
import com.gradetrack.entity.Result;
import com.gradetrack.entity.Student;
import com.gradetrack.exception.DuplicateResourceException;
import com.gradetrack.exception.ResourceNotFoundException;
import com.gradetrack.mapper.ResultMapper;
import com.gradetrack.repository.ResultRepository;
import com.gradetrack.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final ResultMapper resultMapper;

    public ResultService(
            ResultRepository resultRepository,
            StudentRepository studentRepository,
            ResultMapper resultMapper) {

        this.resultRepository = resultRepository;
        this.studentRepository = studentRepository;
        this.resultMapper = resultMapper;
    }


    // ==========================================
    // CREATE RESULT
    // ==========================================

    public ResultResponse createResult(ResultRequest request) {

        Student student = studentRepository.findById(
                request.getStudentId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Student not found with id: "
                                + request.getStudentId()
                )
        );

        if (resultRepository.existsByStudentId(
                request.getStudentId())) {

            throw new DuplicateResourceException(
                    "Result already exists for student id: "
                            + request.getStudentId()
            );
        }


        // Calculate total
        int total =
                request.getEnglish()
                        + request.getMathematics()
                        + request.getPhysics()
                        + request.getChemistry()
                        + request.getComputer()
                        + request.getHindi();


        // Calculate percentage
        double percentage = total / 6.0;


        // Calculate grade
        String grade = calculateGrade(percentage);


        // Calculate PASS / FAIL
        String status = calculateStatus(request);


        // Create Result
        Result result = new Result();

        result.setStudent(student);
        result.setTotal(total);

        result.setPercentage(
                Math.round(percentage * 100.0) / 100.0
        );

        result.setGrade(grade);
        result.setStatus(status);


        // ==========================================
        // Create Marks
        // ==========================================

        Marks marks = new Marks();

        marks.setResult(result);
        marks.setEnglish(request.getEnglish());
        marks.setMathematics(request.getMathematics());
        marks.setPhysics(request.getPhysics());
        marks.setChemistry(request.getChemistry());
        marks.setComputer(request.getComputer());
        marks.setHindi(request.getHindi());


        // Connect relationships
        result.setMarks(marks);
        student.setResult(result);


        // Save result
        Result savedResult =
                resultRepository.save(result);


        // ==========================================
        // Recalculate all ranks
        // ==========================================

        updateRanks();


        // Return fresh result with rank
        Result updatedResult =
                resultRepository.findById(savedResult.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Result not found after saving"
                                )
                        );

        return resultMapper.toResponse(updatedResult);
    }


    // ==========================================
    // GET ALL RESULTS
    // ==========================================

    public List<ResultResponse> getAllResults() {

        return resultRepository
                .findAllByOrderByPercentageDesc()
                .stream()
                .map(resultMapper::toResponse)
                .collect(Collectors.toList());
    }


    // ==========================================
    // GET RESULT BY ID
    // ==========================================

    public ResultResponse getResultById(Long id) {

        Result result = resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Result not found with id: " + id
                        )
                );

        return resultMapper.toResponse(result);
    }


    // ==========================================
    // GET RESULT BY STUDENT ID
    // ==========================================

    public ResultResponse getResultByStudentId(
            Long studentId) {

        if (!studentRepository.existsById(studentId)) {

            throw new ResourceNotFoundException(
                    "Student not found with id: "
                            + studentId
            );
        }

        Result result = resultRepository
                .findByStudentId(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Result not found for student id: "
                                        + studentId
                        )
                );

        return resultMapper.toResponse(result);
    }


    // ==========================================
    // UPDATE RANKS
    // ==========================================

    private void updateRanks() {

        List<Result> results =
                resultRepository.findAllByOrderByPercentageDesc();

        int currentRank = 0;
        double previousPercentage = -1;

        for (int i = 0; i < results.size(); i++) {

            Result result = results.get(i);

            double percentage = result.getPercentage();

            // If percentage is different,
            // rank becomes the actual position.
            if (Double.compare(
                    percentage,
                    previousPercentage) != 0) {

                currentRank = i + 1;
            }

            result.setRank(currentRank);

            previousPercentage = percentage;
        }

        resultRepository.saveAll(results);
    }


    // ==========================================
    // CALCULATE GRADE
    // ==========================================

    private String calculateGrade(double percentage) {

        if (percentage >= 90) {
            return "A+";
        }

        if (percentage >= 80) {
            return "A";
        }

        if (percentage >= 70) {
            return "B";
        }

        if (percentage >= 60) {
            return "C";
        }

        if (percentage >= 40) {
            return "D";
        }

        return "Fail";
    }


    // ==========================================
    // CALCULATE STATUS
    // ==========================================

    private String calculateStatus(
            ResultRequest request) {

        if (request.getEnglish() < 33
                || request.getMathematics() < 33
                || request.getPhysics() < 33
                || request.getChemistry() < 33
                || request.getComputer() < 33
                || request.getHindi() < 33) {

            return "FAIL";
        }

        return "PASS";
    }
}