package com.se.course;

//import packages
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import com.se.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class CourseController {
    /**
     * 显示课程功能界面
     *
     * @param session 当前会话
     * @param courseIndex 课程在list中的索引
     * @return 课程功能界面逻辑视图名
     */
    @RequestMapping("/course/index")
    public String indexPage(HttpSession session, @RequestParam("courseIndex") int courseIndex) {
        session.setAttribute("courseIndex", courseIndex);
        User user = (User)session.getAttribute("user");

        if (user.getType() == 1) {
            return "course/index/student_index";
        } else {
            return "course/index/teacher_index";
        }
    }
}
