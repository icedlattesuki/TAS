package com.se.global.service;

//import packages
import org.springframework.stereotype.Service;

/**
 * @author Yusen
 * @version 1.1
 * @since 1.0
 */
@Service
public class SqlService {
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_PASSWORD = "password";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_COLLEGE = "college";
    public static final String STUDENT_EMAIL = "email";
    public static final String STUDENT_IMAGE_LOCATION = "image_position";
    public static final String STUDENT_SIGNATURE = "signature";
    public static final String STUDENT_PROFILE = "profile";
    public static final String STUDENT_MAJOR = "major";
    public static final String STUDENT_GRADE = "grade";
    public static final String STUDENT_CLASS_NUMBER = "class_number";
    public static final String STUDENT_PHONE_NUMBER = "phone_number";


    public static final String TEACHER_ID = "id";
    public static final String TEACHER_PASSWORD = "password";
    public static final String TEACHER_NAME = "name";
    public static final String TEACHER_COLLEGE = "college";
    public static final String TEACHER_EMAIL = "email";
    public static final String TEACHER_IMAGE_LOCATION = "image_position";
    public static final String TEACHER_SIGNATURE = "signature";
    public static final String TEACHER_PROFILE = "profile";
    public static final String TEACHER_PHONE_NUMBER = "phone_number";
    public static final String TEACHER_TITLE = "title";

    public static final String COURSE_ID = "id";
    public static final String COURSE_CODE = "code";
    public static final String COURSE_NAME = "name";
    public static final String COURSE_CREDIT = "credit";
    public static final String COURSE_COLLEGE = "college";
    public static final String COURSE_NTRODUCTION = "introduction";
    public static final String COURSE_LIKE_NUMBER = "like_number";
    public static final String COURSE_SEMESTER = "semester";
    public static final String COURSE_TIME = "time";
    public static final String COURSE_PLACE = "place";

    public static final String TAKE_STUDENT_ID = "student_id";
    public static final String TAKE_COURSE_ID = "course_id";

    public static final String TEACH_TEACHER_ID = "teacher_id";
    public static final String TEACH_COURSE_ID = "course_id";

    public static final String FILE_ID = "id";
    public static final String FILE_NAME = "name";
    public static final String FILE_LOCATION = "location";
    public static final String FILE_SIZE = "size";
    public static final String FILE_DATE = "date";
    public static final String FILE_COURSE_ID = "course_id";
    public static final String FILE_USER_ID = "user_id";

    public static final String MATERIAL_FILE_ID = "file_id";

    public static final String VIDEO_FILE_ID = "file_id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_PROFILE = "profile";

    public static final String ANNOUNCEMENT_TITLE = "title";
    public static final String ANNOUNCEMENT_CONTENT = "content";
    public static final String ANNOUNCEMENT_DATE = "date";
    public static final String ANNOUNCEMENT_COURSE_ID = "course_id";

    public static final String NOTICE_ID = "id";
    public static final String NOTICE_USER_ID = "user_id";
    public static final String NOTICE_COURSE_ID = "course_id";
    public static final String NOTICE_TYPE = "type";
    public static final String NOTICE_DATE = "date";
    public static final String NOTICE_MESSAGE = "message";

    public static final String COMMENT_ID = "id";
    public static final String COMMENT_USER_ID = "user_id";
    public static final String COMMENT_USER_NAME = "user_name";
    public static final String COMMENT_USER_IMAGE_POSITION = "user_image_position";
    public static final String COMMENT_COURSE_ID = "course_id";
    public static final String COMMENT_DATE = "date";
    public static final String COMMENT_CONTENT = "content";
}
