package com.se.domain;

public class CourseKey {
    private String id;
    private String time;
    private String semester;
    private String place;

    public String getId() {
        return id;
    }

    public String getPlace() { return place; }

    public String getTime() {
        return time;
    }

    public String getSemester() {
        return semester;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlace(String place) { this.place = place; }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSemester(String semester) { this.semester = semester; }
}
