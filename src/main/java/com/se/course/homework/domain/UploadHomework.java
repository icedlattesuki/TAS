package com.se.course.homework.domain;

import com.se.global.domain.File;

import java.util.Date;

public class UploadHomework extends File{
    private int id;
    private String student_id;
    private int homework_id;
    private int get_score;
    private Date handle_date;
    private int course_id;
    private int upload_homework_file;

    public UploadHomework() {}

    public UploadHomework(File file) {
        super(file);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public int getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(int homework_id) {
        this.homework_id = homework_id;
    }

    public int getGet_score() {
        return get_score;
    }

    public void setGet_score(int get_score) {
        this.get_score = get_score;
    }

    public Date getHandle_date() {
        return handle_date;
    }

    public void setHandle_date(Date handle_date) {
        this.handle_date = handle_date;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUpload_homework_file() {
        return upload_homework_file;
    }

    public void setUpload_homework_file(int upload_homework_file) {
        this.upload_homework_file = upload_homework_file;
    }
}
