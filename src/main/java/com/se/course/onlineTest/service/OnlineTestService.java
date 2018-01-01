package com.se.course.onlineTest.service;

import com.se.course.onlineTest.dao.ChoiceQuestionDAO;
import com.se.course.onlineTest.dao.FillQuestionDAO;
import com.se.course.onlineTest.dao.OnlineTestDAO;
import com.se.course.onlineTest.domain.ChoiceQuestion;
import com.se.course.onlineTest.domain.FillQuestion;
import com.se.course.onlineTest.domain.OnlineTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class OnlineTestService {
    private OnlineTestDAO onlineTestDAO;
    private ChoiceQuestionDAO choiceQuestionDAO;
    private FillQuestionDAO fillQuestionDAO;

    @Autowired
    public void setChoiceQuestionDAO(ChoiceQuestionDAO choiceQuestionDAO) {
        this.choiceQuestionDAO = choiceQuestionDAO;
    }

    @Autowired
    public void setFillQuestionDAO(FillQuestionDAO fillQuestionDAO) {
        this.fillQuestionDAO = fillQuestionDAO;
    }

    @Autowired
    public void setOnlineTestDAO(OnlineTestDAO onlineTestDAO) {
        this.onlineTestDAO = onlineTestDAO;
    }

    public boolean createOnlineTest(int courseId, String testTitle, Date ddlDate, String title[], String a[], String b[],
                                    String c[], String d[], String objectiveContent[], String answer[], int score[]) {
        try {
            int totalScore = 0;
            for (int singleScore : score)
                totalScore += singleScore;
            onlineTestDAO.createOnlineTest(courseId, ddlDate, totalScore, testTitle);
            int testId = onlineTestDAO.getOnlineTest(courseId, testTitle);
            for (int i = 0; i < a.length; i++) {
                choiceQuestionDAO.createQuestion(testId, score[i], title[i], a[i], b[i], c[i], d[i], answer[i]);
            }
            for (int i = a.length; i < title.length; i++) {
                fillQuestionDAO.createQuestion(testId, score[i], title[i], objectiveContent[i-a.length]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<OnlineTest> getCourseOnlineTest(int courseId) {
        try {
            return onlineTestDAO.getCourseOnlineTest(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public OnlineTest getOnlineTestById(int id) {
        try {
            return onlineTestDAO.getOnlineTestById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ChoiceQuestion> getChoiceQuestions(int testId) {
        try {
            return choiceQuestionDAO.getChoiceQuestions(testId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<FillQuestion> getFillQuestions(int testId) {
        try {
            return fillQuestionDAO.getFillQuestions(testId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
