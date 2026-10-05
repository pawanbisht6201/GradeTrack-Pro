package com.gradetrack.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ResultRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "English marks are required")
    @Min(value = 0, message = "English marks cannot be less than 0")
    @Max(value = 100, message = "English marks cannot exceed 100")
    private Integer english;

    @NotNull(message = "Mathematics marks are required")
    @Min(value = 0, message = "Mathematics marks cannot be less than 0")
    @Max(value = 100, message = "Mathematics marks cannot exceed 100")
    private Integer mathematics;

    @NotNull(message = "Physics marks are required")
    @Min(value = 0, message = "Physics marks cannot be less than 0")
    @Max(value = 100, message = "Physics marks cannot exceed 100")
    private Integer physics;

    @NotNull(message = "Chemistry marks are required")
    @Min(value = 0, message = "Chemistry marks cannot be less than 0")
    @Max(value = 100, message = "Chemistry marks cannot exceed 100")
    private Integer chemistry;

    @NotNull(message = "Computer marks are required")
    @Min(value = 0, message = "Computer marks cannot be less than 0")
    @Max(value = 100, message = "Computer marks cannot exceed 100")
    private Integer computer;

    @NotNull(message = "Hindi marks are required")
    @Min(value = 0, message = "Hindi marks cannot be less than 0")
    @Max(value = 100, message = "Hindi marks cannot exceed 100")
    private Integer hindi;

    public ResultRequest() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getMathematics() {
        return mathematics;
    }

    public void setMathematics(Integer mathematics) {
        this.mathematics = mathematics;
    }

    public Integer getPhysics() {
        return physics;
    }

    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public Integer getComputer() {
        return computer;
    }

    public void setComputer(Integer computer) {
        this.computer = computer;
    }

    public Integer getHindi() {
        return hindi;
    }

    public void setHindi(Integer hindi) {
        this.hindi = hindi;
    }
}