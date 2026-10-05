package com.gradetrack.analytics;

public class SubjectAnalytics {

    private double english;
    private double mathematics;
    private double physics;
    private double chemistry;
    private double computer;
    private double hindi;

    public SubjectAnalytics() {
    }

    public SubjectAnalytics(
            double english,
            double mathematics,
            double physics,
            double chemistry,
            double computer,
            double hindi) {

        this.english = english;
        this.mathematics = mathematics;
        this.physics = physics;
        this.chemistry = chemistry;
        this.computer = computer;
        this.hindi = hindi;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public double getMathematics() {
        return mathematics;
    }

    public void setMathematics(double mathematics) {
        this.mathematics = mathematics;
    }

    public double getPhysics() {
        return physics;
    }

    public void setPhysics(double physics) {
        this.physics = physics;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }

    public double getComputer() {
        return computer;
    }

    public void setComputer(double computer) {
        this.computer = computer;
    }

    public double getHindi() {
        return hindi;
    }

    public void setHindi(double hindi) {
        this.hindi = hindi;
    }
}