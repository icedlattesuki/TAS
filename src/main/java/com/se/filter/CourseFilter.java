package com.se.filter;

//import packages
import com.se.courses.course.service.CourseService;
import com.se.global.domain.User;
import com.se.global.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Order(2)
@WebFilter(filterName = "courseFilter", urlPatterns = "/course/*")
public class CourseFilter implements Filter {
    private CourseService courseService;
    private static final Logger logger = LoggerFactory.getLogger("CourseFilter.class");

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void init(FilterConfig var1) throws ServletException {
        logger.info("CourseFilter start!");
    }

    @Override
    public void destroy() {
        logger.info("CourseFilter destroy!");
    }

    //阻止用户通过url直接进入课程操作相关界面
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = httpServletRequest.getSession();
        String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        User user = SessionService.getUser(session);

        if (user == null || user.getType() == User.ADMIN_TYPE) {
            httpServletResponse.sendRedirect("/index");
            return;
        }

        String[] s = url.split("/");
        int courseId = Integer.parseInt(s[2]);

        if (!courseService.identifyCourseAndUser(courseId, user)) {
            httpServletResponse.sendRedirect("/index");
            return;
        }

        chain.doFilter(request, response);
    }
}
