package com.se.course.resource.domain;

//import packages
import com.se.global.domain.CourseKey;
import java.util.Date;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Resource {
    //0表示资料，1表示视频
    private int type;
    private String name;
    private String location;
    private long size;
    private Date date;
    private String title;
    private String profile;
    private CourseKey courseKey;
    //以B、KB、MB、GB为单位的字符串类型的文件大小表示，在数据库中没有对应字段
    private String size1;

    public Resource() {
        title = "";
        profile = "";
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public CourseKey getCourseKey() {
        return courseKey;
    }

    public void setCourseKey(CourseKey courseKey) {
        this.courseKey = courseKey;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }
}
