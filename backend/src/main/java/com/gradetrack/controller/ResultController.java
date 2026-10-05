package com.gradetrack.controller;

import com.gradetrack.dto.ResultRequest;
import com.gradetrack.dto.ResultResponse;
import com.gradetrack.service.ResultService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }


    // ==========================================
    // CREATE RESULT
    // ==========================================

    @PostMapping
    public ResponseEntity<ResultResponse> createResult(
            @Valid @RequestBody ResultRequest request) {

        ResultResponse response =
                resultService.createResult(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // ==========================================
    // GET ALL RESULTS
    // ==========================================

    @GetMapping
    public ResponseEntity<List<ResultResponse>> getAllResults() {

        return ResponseEntity.ok(
                resultService.getAllResults()
        );
    }


    // ==========================================
    // GET RESULT BY ID
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getResultById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resultService.getResultById(id)
        );
    }


    // ==========================================
    // GET RESULT BY STUDENT ID
    // ==========================================

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ResultResponse> getResultByStudentId(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                resultService.getResultByStudentId(studentId)
        );
    }
}