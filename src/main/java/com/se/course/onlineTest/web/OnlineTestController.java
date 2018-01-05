package com.se.course.onlineTest.web;

import com.se.course.onlineTest.domain.ChoiceQuestion;
import com.se.course.onlineTest.domain.FillQuestion;
import com.se.course.onlineTest.domain.OnlineTest;
import com.se.course.onlineTest.service.OnlineTestService;
import com.se.global.domain.User;
import com.se.global.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class OnlineTestController {
    private OnlineTestService onlineTestService;

    @Autowired
    public void setOnlineTestService(OnlineTestService onlineTestService) {
        this.onlineTestService = onlineTestService;
    }

    @RequestMapping("/course/{courseId}/onlineTest/to-create")
    public String createOnlineTestPage(HttpSession session, Model model, @PathVariable int courseId) {
        model.addAttribute("courseId", courseId);
        return "course/online_test/create_test";
    }

    @RequestMapping("/course/{courseId}/onlineTest/list")
    public String onlineTestListPage(HttpSession session, Model model, @PathVariable int courseId) {
        ArrayList<OnlineTest> onlineTests = onlineTestService.getCourseOnlineTest(courseId);
        model.addAttribute("onlineTests", onlineTests);
        User user = SessionService.getUser(session);
        if (user.getType() == 2)
            return "course/online_test/online_test_list_tea";
        else
            return "course/online_test/online_test_list_stu";
    }

    @RequestMapping("/course/{courseId}/onlineTest/create")
    public String createOnlineTest(HttpSession session, Model model, @Valid OnlineTestCommand onlineTestCommand,
                                   BindingResult bindingResult, @PathVariable int courseId) {

        if (onlineTestService.createOnlineTest(courseId, onlineTestCommand.getTestTitle(), onlineTestCommand.getDdlDate(),
                onlineTestCommand.getTitle(), onlineTestCommand.getA(), onlineTestCommand.getB(), onlineTestCommand.getC(),
                onlineTestCommand.getD(), onlineTestCommand.getObjectiveContent(), onlineTestCommand.getAnswer(), onlineTestCommand.getScore())) {
            return "redirect:/course/" + courseId + "/onlineTest/list";
        } else {
            model.addAttribute("error", "上传错误");
            return "redirect:/course/" + courseId + "/onlineTest/to-create";
        }
    }

    @RequestMapping("/course/{courseId}/onlineTest/{onlineTestId}")
    public String onlineTestDetailPage(HttpSession session, Model model, @PathVariable int courseId,
                                       @PathVariable int onlineTestId) {
        OnlineTest onlineTest = onlineTestService.getOnlineTestById(onlineTestId);
        if (onlineTest != null) {
            ArrayList<ChoiceQuestion> choiceQuestions = onlineTestService.getChoiceQuestions(onlineTestId);
            ArrayList<FillQuestion> fillQuestions = onlineTestService.getFillQuestions(onlineTestId);
            model.addAttribute("onlineTest", onlineTest);
            model.addAttribute("choiceQuestions", choiceQuestions);
            model.addAttribute("fillQuestions", fillQuestions);
            User user = SessionService.getUser(session);
            if (user.getType() == 2)
                return "course/online_test/online_test_detail_tea";
            else
                return "course/online_test/online_test_detail_stu";
        } else {
            return "error/404";
        }
    }

    @RequestMapping("/course/{courseId}/onlineTest/{onlineTestId}/delete")
    public String deleteOnlineTest(HttpSession session, Model model, @PathVariable int courseId,
                                   @PathVariable int onlineTestId) {
        onlineTestService.deleteOnlineTest(onlineTestId);
        return "redirect:/course/" + courseId + "/onlineTest/list";
    }

    @RequestMapping("/course/{courseId}/onlineTest/{onlineTestId}/rate")
    public String rateOnlineTestPage(HttpSession session, Model model) {
        return "/course/online_test/rate";
    }
}
