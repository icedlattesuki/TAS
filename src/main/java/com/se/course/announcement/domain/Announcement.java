package com.se.course.announcement.domain;

//import packages
import java.util.Date;
import com.se.global.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Announcement {
    private String title;
    private String content;
    private Date date;
    private CourseKey courseKey;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CourseKey getCourseKey() {
        return courseKey;
    }

    public void setCourseKey(CourseKey courseKey) {
        this.courseKey = courseKey;
    }
}
