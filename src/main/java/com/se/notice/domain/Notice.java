package com.se.notice.domain;

import java.util.ArrayList;

public class Notice {
    private String userId;
    private int totalNumber;
    private ArrayList<CourseNotice> courseNoticeList;
    public static final String MESSAGE_ID = "message_id";
    public static final String USER_ID = "user_id";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String MESSAGE = "message";

    public Notice() {
        totalNumber = 0;
        courseNoticeList = new ArrayList<CourseNotice>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public ArrayList<CourseNotice> getCourseNoticeList() {
        return courseNoticeList;
    }

    public void setCourseNoticeList(ArrayList<CourseNotice> courseNoticeList) {
        this.courseNoticeList = courseNoticeList;
    }
}
