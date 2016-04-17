package kxw;

import java.io.Serializable;

public class StudentGrade implements Serializable {
//班级课程成绩类
    private String grade_name;
    private String course;
    private String[] student;
    private double[] grade;

    public StudentGrade() {
    }

    public StudentGrade(String course, int n, String gradeName) {
        this.course = course;
        this.grade = new double[n];
        this.student = new String[n];
        this.grade_name = gradeName;
    }

    public double[] getGrade() {
        return grade;
    }

    public String getCourse() {
        return this.course;
    }

    public String[] getStudent() {
        return this.student;
    }

    public String getGrade_name() {
        return this.grade_name;
    }
}
