package com.se.global.domain;

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
    private String grade;
    private ArrayList<Integer> takes;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ArrayList<Integer> getTakes() {
        return takes;
    }

    public void setTakes(ArrayList<Integer> takes) {
        this.takes = takes;
    }
}
