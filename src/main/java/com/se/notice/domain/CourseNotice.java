package com.se.notice.domain;

import com.se.global.domain.Course;

import java.util.ArrayList;

public class CourseNotice {
    private Course course;
    private int totalNumber;
    private ArrayList<DetailNotice> detailNoticeList;

    public CourseNotice() {
        totalNumber = 0;
        detailNoticeList = new ArrayList<DetailNotice>();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public ArrayList<DetailNotice> getDetailNoticeList() {
        return detailNoticeList;
    }

    public void setDetailNoticeList(ArrayList<DetailNotice> detailNoticeList) {
        this.detailNoticeList = detailNoticeList;
    }
}
