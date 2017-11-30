package com.se.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.*;
import java.util.*;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {
    private static List<Pattern> patterns = new ArrayList<Pattern>();
    private static final Logger logger = LoggerFactory.getLogger("SessionFilter.class");

     //注册不需要过滤的URL
    static {
        patterns.add(Pattern.compile("/"));
        patterns.add(Pattern.compile("/index"));
        patterns.add(Pattern.compile("/login"));
        patterns.add(Pattern.compile("/login/check"));
        patterns.add(Pattern.compile("/image/*"));
        patterns.add(Pattern.compile("/user/email/bind"));
        patterns.add(Pattern.compile("/user/email/unbind"));
        patterns.add(Pattern.compile("/user/password-reset/reset"));
        patterns.add(Pattern.compile("/user/password-reset/to-reset"));
        patterns.add(Pattern.compile("/user/password-reset"));
    }

    @Override
    public void init(FilterConfig var1) throws ServletException {
        logger.info("sessionFilter start!");
    }

    @Override
    public void destroy() {
        logger.info("sessionFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        if (!isInclude(url)) {
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("user") == null) {
                httpServletResponse.sendRedirect("/");
                return;
            }
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
