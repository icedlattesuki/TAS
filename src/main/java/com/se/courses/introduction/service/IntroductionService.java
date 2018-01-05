package com.se.courses.introduction.service;

import com.se.courses.introduction.dao.IntroductionDAO;
import com.se.global.domain.Course;
import com.se.global.domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IntroductionService {
    private IntroductionDAO introductionDAO;
    private final Logger logger = LoggerFactory.getLogger("IntroductionService.class");

    @Autowired
    public void setIntroductionDAO(IntroductionDAO introductionDAO) {
        this.introductionDAO = introductionDAO;
    }

    public Course getCourse(int courseId) {
        try {
            return introductionDAO.getCourse(courseId);
        } catch (Exception e) {
            logger.error("getCourse fail! " + e.getMessage());
            return new Course();
        }
    }

    public ArrayList<Teacher> getTeachers(int courseId) {
        try {
            return introductionDAO.getTeachers(courseId);
        } catch (Exception e) {
            logger.error("getTeachers fail! " + e.getMessage());
            return new ArrayList<Teacher>();
        }
    }

    public void submitIntro(int courseId, String intro) {
        try {
            introductionDAO.submitIntro(courseId, intro);
        } catch (Exception e) {
            logger.error("submitIntro fail! " + e.getMessage());
        }
    }
}
