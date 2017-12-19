package com.se.course.homework.web;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HomeworkAssignCommand {
    private String title;
    private Date ddl;
    private int score;
    private String content;
    private String attachment;

    public void setTitle(String title) {
        this.title = title;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setDdl(Date ddl) {
        this.ddl = ddl;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getTitle() {
        return title;
    }

    public Date getDdl() {
        return ddl;
    }

    public int getScore() {
        return score;
    }

    public String getContent() {
        return content;
    }

    public String getAttachment() {
        return attachment;
    }
}
