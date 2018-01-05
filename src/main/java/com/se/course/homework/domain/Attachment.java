package com.se.course.homework.domain;

import com.se.global.domain.File;

public class Attachment extends File{
    private int homework_id;
    private int file_id;

    public Attachment() {}

    public Attachment(File file) {
        super(file);
    }

    public int getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(int homework_id) {
        this.homework_id = homework_id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }
}
