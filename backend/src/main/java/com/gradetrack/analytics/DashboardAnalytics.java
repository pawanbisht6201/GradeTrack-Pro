package com.gradetrack.analytics;

public class DashboardAnalytics {

    private long totalStudents;
    private long totalResults;
    private double averagePercentage;
    private double passPercentage;
    private double failPercentage;
    private double highestPercentage;

    public DashboardAnalytics() {
    }

    public DashboardAnalytics(
            long totalStudents,
            long totalResults,
            double averagePercentage,
            double passPercentage,
            double failPercentage,
            double highestPercentage) {

        this.totalStudents = totalStudents;
        this.totalResults = totalResults;
        this.averagePercentage = averagePercentage;
        this.passPercentage = passPercentage;
        this.failPercentage = failPercentage;
        this.highestPercentage = highestPercentage;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public double getAveragePercentage() {
        return averagePercentage;
    }

    public void setAveragePercentage(double averagePercentage) {
        this.averagePercentage = averagePercentage;
    }

    public double getPassPercentage() {
        return passPercentage;
    }

    public void setPassPercentage(double passPercentage) {
        this.passPercentage = passPercentage;
    }

    public double getFailPercentage() {
        return failPercentage;
    }

    public void setFailPercentage(double failPercentage) {
        this.failPercentage = failPercentage;
    }

    public double getHighestPercentage() {
        return highestPercentage;
    }

    public void setHighestPercentage(double highestPercentage) {
        this.highestPercentage = highestPercentage;
    }
}