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
    public static final String FILE_ID = "id";
    public static final String FILE_NAME = "name";
    public static final String FILE_LOCATION = "location";
    public static final String FILE_SIZE = "size";
    public static final String FILE_DATE = "date";
    public static final String FILE_COURSE_ID = "course_id";
    public static final String FILE_SEMESTER = "semester";
    public static final String FILE_TIME = "time";
    public static final String FILE_PLACE = "place";
    public static final String FILE_USER_ID = "user_id";

    public static final String MATERIAL_FILE_ID = "file_id";

    public static final String VIDEO_FILE_ID = "file_id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_PROFILE = "profile";

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
