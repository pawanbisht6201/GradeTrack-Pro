package com.gradetrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "result_id", nullable = false, unique = true)
    private Result result;

    @Column(nullable = false)
    private Integer english;

    @Column(nullable = false)
    private Integer mathematics;

    @Column(nullable = false)
    private Integer physics;

    @Column(nullable = false)
    private Integer chemistry;

    @Column(nullable = false)
    private Integer computer;

    @Column(nullable = false)
    private Integer hindi;

    public Marks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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