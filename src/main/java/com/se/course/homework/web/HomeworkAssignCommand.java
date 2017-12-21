package com.se.course.homework.web;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class HomeworkAssignCommand implements Serializable {
    private String title;
    private Date ddl;
    private int score;
    private String content;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

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
}
