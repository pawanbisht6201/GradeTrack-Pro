package com.gradetrack.service;

import com.gradetrack.analytics.DashboardAnalytics;
import com.gradetrack.analytics.GradeDistribution;
import com.gradetrack.analytics.SubjectAnalytics;
import com.gradetrack.entity.Marks;
import com.gradetrack.entity.Result;
import com.gradetrack.repository.ResultRepository;
import com.gradetrack.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;

    public AnalyticsService(
            StudentRepository studentRepository,
            ResultRepository resultRepository) {

        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
    }

    // Dashboard Analytics
    public DashboardAnalytics getDashboardAnalytics() {

        long totalStudents = studentRepository.count();

        List<Result> results = resultRepository.findAll();

        long totalResults = results.size();

        if (results.isEmpty()) {
            return new DashboardAnalytics(
                    totalStudents,
                    0,
                    0.0,
                    0.0,
                    0.0,
                    0.0
            );
        }

        double totalPercentage = 0.0;
        long passCount = 0;
        long failCount = 0;
        double highestPercentage = 0.0;

        for (Result result : results) {

            double percentage = result.getPercentage();

            totalPercentage += percentage;

            if ("PASS".equalsIgnoreCase(result.getStatus())) {
                passCount++;
            } else {
                failCount++;
            }

            if (percentage > highestPercentage) {
                highestPercentage = percentage;
            }
        }

        double averagePercentage =
                totalPercentage / totalResults;

        double passPercentage =
                (passCount * 100.0) / totalResults;

        double failPercentage =
                (failCount * 100.0) / totalResults;

        return new DashboardAnalytics(
                totalStudents,
                totalResults,
                Math.round(averagePercentage * 100.0) / 100.0,
                Math.round(passPercentage * 100.0) / 100.0,
                Math.round(failPercentage * 100.0) / 100.0,
                highestPercentage
        );
    }

    // Grade Distribution Analytics
    public GradeDistribution getGradeDistribution() {

        List<Result> results = resultRepository.findAll();

        long aPlus = 0;
        long a = 0;
        long b = 0;
        long c = 0;
        long d = 0;
        long fail = 0;

        for (Result result : results) {

            String grade = result.getGrade();

            if ("A+".equalsIgnoreCase(grade)) {
                aPlus++;
            } else if ("A".equalsIgnoreCase(grade)) {
                a++;
            } else if ("B".equalsIgnoreCase(grade)) {
                b++;
            } else if ("C".equalsIgnoreCase(grade)) {
                c++;
            } else if ("D".equalsIgnoreCase(grade)) {
                d++;
            } else {
                fail++;
            }
        }

        return new GradeDistribution(
                aPlus,
                a,
                b,
                c,
                d,
                fail
        );
    }

    // Subject-wise Analytics
    public SubjectAnalytics getSubjectAnalytics() {

        List<Result> results = resultRepository.findAll();

        if (results.isEmpty()) {
            return new SubjectAnalytics(
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0
            );
        }

        double englishTotal = 0.0;
        double mathematicsTotal = 0.0;
        double physicsTotal = 0.0;
        double chemistryTotal = 0.0;
        double computerTotal = 0.0;
        double hindiTotal = 0.0;

        int count = 0;

        for (Result result : results) {

            Marks marks = result.getMarks();

            if (marks == null) {
                continue;
            }

            englishTotal += marks.getEnglish();
            mathematicsTotal += marks.getMathematics();
            physicsTotal += marks.getPhysics();
            chemistryTotal += marks.getChemistry();
            computerTotal += marks.getComputer();
            hindiTotal += marks.getHindi();

            count++;
        }

        if (count == 0) {
            return new SubjectAnalytics(
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0.0
            );
        }

        double englishAverage = englishTotal / count;
        double mathematicsAverage = mathematicsTotal / count;
        double physicsAverage = physicsTotal / count;
        double chemistryAverage = chemistryTotal / count;
        double computerAverage = computerTotal / count;
        double hindiAverage = hindiTotal / count;

        return new SubjectAnalytics(
                roundToTwoDecimals(englishAverage),
                roundToTwoDecimals(mathematicsAverage),
                roundToTwoDecimals(physicsAverage),
                roundToTwoDecimals(chemistryAverage),
                roundToTwoDecimals(computerAverage),
                roundToTwoDecimals(hindiAverage)
        );
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}