package com.se.domain;

/**
 * 封装课程属性
 *
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Course {
    private String id;
    private String name;
    private String introduction;
    private float credit;
    private String college;
    private String time;
    private String semester;
    private String place;
    private int like;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public float getCredit() {
        return credit;
    }

    public String getCollege() {
        return college;
    }

    public String getTime() {
        return time;
    }

    public String getSemester() {
        return semester;
    }

    public String getPlace() {
        return place;
    }

    public int getLike() {
        return like;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLike(int like) { this.like = like; }
}
