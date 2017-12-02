package com.se.course.resource.domain;

//import packages
import com.se.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Resource {
    private String name;
    private String location;
    private long size;
    private CourseKey courseKey;
    //以B、KB、MB、GB为单位的字符串类型的文件大小表示，在数据库中没有对应字段
    private String size1;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public long getSize() {
        return size;
    }

    public CourseKey getCourseKey() {
        return courseKey;
    }

    public String getSize1() {
        return size1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setCourseKey(CourseKey courseKey) {
        this.courseKey = courseKey;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }
}
