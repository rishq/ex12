package org.hwork.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "studentId")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "groupNum")
    private Integer group_number;

    @Column(name = "avgScore")
    private Double avg_score;

    public Student(UUID id, String name, Integer group_number, Double avg_score) {
        this.id = id;
        this.name = name;
        this.group_number = group_number;
        this.avg_score = avg_score;
    }

    public Student() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup_number(Integer group_number) {
        this.group_number = group_number;
    }

    public void setAvg_score(Double avg_score) {
        this.avg_score = avg_score;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getGroup_number() {
        return group_number;
    }

    public Double getAvg_score() {
        return avg_score;
    }
}
