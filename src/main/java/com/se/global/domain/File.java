package com.se.global.domain;

import java.util.Date;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class File {
    private int id;
    private String name;
    private String location;
    private long size;
    private String size1;
    private Date date;
    private int courseId;
    private String userId;
    public static String ROOT_PATH = "/Users/yusen/Desktop/files";

    public File() {

    }

    public File(File file) {
        this.id = file.id;
        this.name = file.name;
        this.location = file.location;
        this.size = file.size;
        this.size1 = file.size1;
        this.date = file.date;
        this.courseId = file.courseId;
        this.userId = file.userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
