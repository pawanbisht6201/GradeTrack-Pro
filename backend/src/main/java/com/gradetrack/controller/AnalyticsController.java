package com.gradetrack.controller;

import com.gradetrack.analytics.DashboardAnalytics;
import com.gradetrack.analytics.GradeDistribution;
import com.gradetrack.analytics.SubjectAnalytics;
import com.gradetrack.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // Dashboard analytics
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardAnalytics> getDashboardAnalytics() {

        DashboardAnalytics analytics =
                analyticsService.getDashboardAnalytics();

        return ResponseEntity.ok(analytics);
    }

    // Grade distribution
    @GetMapping("/grades")
    public ResponseEntity<GradeDistribution> getGradeDistribution() {

        GradeDistribution gradeDistribution =
                analyticsService.getGradeDistribution();

        return ResponseEntity.ok(gradeDistribution);
    }

    // Subject-wise analytics
    @GetMapping("/subjects")
    public ResponseEntity<SubjectAnalytics> getSubjectAnalytics() {

        SubjectAnalytics subjectAnalytics =
                analyticsService.getSubjectAnalytics();

        return ResponseEntity.ok(subjectAnalytics);
    }
}