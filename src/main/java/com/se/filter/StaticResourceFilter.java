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
import java.net.URLDecoder;
import java.util.regex.Pattern;
import com.se.domain.User;
import com.se.course.resource.service.ResourceService;
import com.se.domain.CourseKey;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Order(2)
@WebFilter(filterName = "staticResourceFilter", urlPatterns = {"/image/*", "/resource/*", "/video/*"})
public class StaticResourceFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger("StaticResourceFilter.class");

    @Override
    public void init(FilterConfig var1) throws ServletException {
        logger.info("StaticResourceFilter start!");
    }

    @Override
    public void destroy() {
        logger.info("StaticResourceFilter destroy!");
    }

    //对静态资源的访问进行过滤
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        //对URL中的中文进行解码
        String url = URLDecoder.decode(httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length()), "UTF-8");
        User user = (User)session.getAttribute("user");

        //图片资源
        if (Pattern.compile("/image/" + user.getId() + ".jpg").matcher(url).matches()) {
            chain.doFilter(request, response);
            return;
        } else {
            CourseKey courseKey = ResourceService.getCourseKey(session);

            if (courseKey == null) {
                httpServletResponse.sendRedirect("/index");
                return;
            }

            //资料及视频
            if (Pattern.compile(ResourceService.getFilePath(courseKey, 0) + ".+").matcher(url).matches()) {
                    chain.doFilter(request, response);
                    return;
            } else if (Pattern.compile(ResourceService.getFilePath(courseKey, 1) + ".+").matcher(url).matches()) {
                    chain.doFilter(request, response);
                    return;
            }
        }

        httpServletResponse.sendRedirect("/index");
    }
}
