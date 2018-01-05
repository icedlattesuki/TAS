package com.se.course.onlineTest.web;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OnlineTestCommand {
    private String testTitle;
    private String title[];
    private String a[], b[], c[], d[];
    private String objectiveContent[];
    private String answer[];
    private int score[];
    private Date ddlDate;

    public Date getDdlDate() {
        return ddlDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setDdlDate(Date ddlDate) {
        this.ddlDate = ddlDate;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int[] getScore() {
        return score;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public String[] getObjectiveContent() {
        return objectiveContent;
    }

    public void setObjectiveContent(String[] objectiveContent) {
        this.objectiveContent = objectiveContent;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getA() {
        return a;
    }

    public void setA(String[] a) {
        this.a = a;
    }

    public String[] getB() {
        return b;
    }

    public void setB(String[] b) {
        this.b = b;
    }

    public String[] getC() {
        return c;
    }

    public void setC(String[] c) {
        this.c = c;
    }

    public String[] getD() {
        return d;
    }

    public void setD(String[] d) {
        this.d = d;
    }
}
