package com.se.global.service;

//import packages
import org.springframework.stereotype.Service;
import com.se.global.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SqlService {
    public static String courseKeyInColumn() {
        return " " + CourseKey.COURSE_ID + "," + CourseKey.SEMESTER + "," + CourseKey.TIME + "," + CourseKey.PLACE + " ";
    }

    public static String courseKeyInWhereClause() {
        return " " + CourseKey.COURSE_ID + " = ? and " + CourseKey.SEMESTER + " = ? and " + CourseKey.TIME + " = ? and " + CourseKey.PLACE + " = ? ";
    }

    public static String courseKeyInWhereClause1() {
        return " " + CourseKey.ID + " = ? and " + CourseKey.SEMESTER + " = ? and " + CourseKey.TIME + " = ? and " + CourseKey.PLACE + " = ? ";
    }
}
