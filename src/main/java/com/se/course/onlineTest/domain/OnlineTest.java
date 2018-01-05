package com.se.course.onlineTest.domain;

import java.util.Date;

public class OnlineTest {
    private int id;
    private int course_id;
    private Date ddl_date;
    private int score;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Date getDdl_date() {
        return ddl_date;
    }

    public void setDdl_date(Date ddl_date) {
        this.ddl_date = ddl_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
