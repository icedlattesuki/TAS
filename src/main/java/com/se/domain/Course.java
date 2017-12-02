package com.se.domain;

/**
 * 封装课程属性
 *
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Course {
    private CourseKey courseKey;
    private String name;
    private String introduction;
    private float credit;
    private String college;
    private int like;

    public CourseKey getCourseKey() {
        return courseKey;
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

    public int getLike() {
        return like;
    }

    public void setCourseKey(CourseKey courseKey) {
        this.courseKey = courseKey;
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

    public void setLike(int like) { this.like = like; }
}
