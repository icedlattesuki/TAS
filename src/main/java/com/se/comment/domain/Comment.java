package com.se.comment.domain;

//import packages
import java.util.Date;
import com.se.global.domain.CourseKey;
import com.se.global.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Comment {
    private int commentId;
    private CourseKey courseKey;
    private User user;
    private Date date;
    private String content;
    public static final String COMMENT_ID = "comment_id";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_IMAGE_POSITION = "user_image_position";
    public static final String DATE = "date";
    public static final String CONTENT = "content";

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public CourseKey getCourseKey() {
        return courseKey;
    }

    public void setCourseKey(CourseKey courseKey) {
        this.courseKey = courseKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
