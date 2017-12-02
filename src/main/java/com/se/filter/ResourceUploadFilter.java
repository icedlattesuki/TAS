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
import com.se.domain.User;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Order(3)
@WebFilter(filterName = "resourceUploadFilter", urlPatterns = "/course/resource-upload/*")
public class ResourceUploadFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger("ResourceUploadFilter.class");

    @Override
    public void init(FilterConfig var1) throws ServletException {
        logger.info("ResourceUploadFilter start!");
    }

    @Override
    public void destroy() {
        logger.info("ResourceUploadFilter destroy!");
    }

    //阻止游客和学生上传资料
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null || user.getType() == 1) {
            httpServletResponse.sendRedirect("/index");
            return;
        }

        chain.doFilter(request, response);
    }
}
