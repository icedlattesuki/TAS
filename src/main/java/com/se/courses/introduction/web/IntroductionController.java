package com.se.courses.introduction.web;

import com.se.courses.introduction.service.IntroductionService;
import com.se.global.domain.User;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class IntroductionController {
    private IntroductionService introductionService;

    @Autowired
    public void setIntroductionService(IntroductionService introductionService) {
        this.introductionService = introductionService;
    }

    @RequestMapping("/course/{courseId}/introduction")
    public String introPage(@PathVariable("courseId") int courseId, HttpSession session, Model model) {
        ModelService.setCourse(model, introductionService.getCourse(courseId));
        ModelService.setTeachers(model, introductionService.getTeachers(courseId));
        ModelService.setNoticeTotalNum(model, session);

        User user = SessionService.getUser(session);

        if (user.getType() == User.STUDENT_TYPE) {
            return "courses/introduction/student_course_intro";
        } else {
            return "courses/introduction/teacher_course_intro";
        }
    }

    @RequestMapping("/course/{courseId}/introduction/edit")
    public String editPage(@PathVariable("courseId") int courseId, HttpSession session, Model model) {
        ModelService.setNoticeTotalNum(model, session);
        return "courses/introduction/course_intro_edit";
    }

    @RequestMapping("/course/{courseId}/introduction/submit")
    public String submitIntro(@PathVariable("courseId") int courseId, @RequestParam("intro") String intro) {
        introductionService.submitIntro(courseId, intro);
        return "redirect:/course/" + courseId + "/introduction";
    }
}
