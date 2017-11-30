package com.se.domain;

import java.util.ArrayList;

/**
 * 封装学生用户除了共有属性之外的属性
 *
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Student extends User {
    private String major;
    private String classNumber;
    private int grade;
    private ArrayList<CourseKey> takes;

    public String getMajor() {
        return major;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public int getGrade() {
        return grade;
    }

    public ArrayList<CourseKey> getTakes() { return takes; }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setTakes(ArrayList<CourseKey> takes) { this.takes = takes; }
}
