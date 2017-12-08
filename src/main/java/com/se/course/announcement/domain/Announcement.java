package com.se.course.announcement.domain;

//import packages
import java.util.Date;
import com.se.domain.CourseKey;

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

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public CourseKey getCourseKey() {
        return courseKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCourseKey(CourseKey courseKey) {
        this.courseKey = courseKey;
    }
}
