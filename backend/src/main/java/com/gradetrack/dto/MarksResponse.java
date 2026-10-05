package com.gradetrack.dto;

public class MarksResponse {

    private Long id;
    private Integer english;
    private Integer mathematics;
    private Integer physics;
    private Integer chemistry;
    private Integer computer;
    private Integer hindi;

    public MarksResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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