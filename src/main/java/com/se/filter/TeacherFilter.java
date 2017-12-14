package com.se.filter;

//import packages
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.se.global.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Order(4)
@WebFilter(filterName = "teacherFilter", urlPatterns = {"/course/resource/*", "/course/comment/remove"})
public class TeacherFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger("ResourceUploadFilter.class");
    private static List<Pattern> patterns = new ArrayList<Pattern>();

    static {
        patterns.add(Pattern.compile("/course/resource/.+/.*upload"));
        patterns.add(Pattern.compile("/course/resource/.+/delete"));
        patterns.add(Pattern.compile("/course/comment/remove"));
    }

    @Override
    public void init(FilterConfig var1) throws ServletException {
        logger.info("ResourceUploadFilter start!");
    }

    @Override
    public void destroy() {
        logger.info("ResourceUploadFilter destroy!");
    }

    //阻止游客和学生上传资料及视频
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");
        String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        if (isInclude(url) && (user == null || user.getType() == 1)) {
            httpServletResponse.sendRedirect("/index");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches())
                return true;
        }

        return false;
    }
}
