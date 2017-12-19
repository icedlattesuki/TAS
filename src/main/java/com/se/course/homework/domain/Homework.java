package com.se.course.homework.domain;

import com.se.global.domain.CourseKey;

import java.util.Date;

public class Homework {
    private int id;
    private String title;
    private String content;
    private Date create_date;
    private Date ddl_date;
    private int score;
    private String attachments;
    private CourseKey courseKey;
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String DDL_DATE = "ddl_date";
    public static final String DATE = "create_date";
    public static final String ATTACHMENT = "attachment";
    public static final String SCORE = "score";

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public CourseKey getCourseKey() {
        return courseKey;
    }

    public void setCourseKey(CourseKey courseKey) {
        this.courseKey = courseKey;
    }
}
