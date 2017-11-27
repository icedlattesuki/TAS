package com.se.domain;

import java.util.ArrayList;

public class Teacher extends User {
    private String title;
    private ArrayList<CourseKey> teaches;

    public String getTitle() { return title; }

    public ArrayList<CourseKey> getTeaches() { return teaches; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeaches(ArrayList<CourseKey> teaches) { this.teaches = teaches; }
}
