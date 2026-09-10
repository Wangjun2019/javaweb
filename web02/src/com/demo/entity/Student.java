package com.demo.entity;

public class Student {
    private Integer studentId;
    private String studentName;
    private Double score;

    public Student() {
    }

    public Student(Integer studentId, String studentName, Double score) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.score = score;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
