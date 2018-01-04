package com.se.filter;

import com.se.global.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Order(4)
@WebFilter(filterName = "adminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger("AdminFilter.class");

    @Override
    public void init(FilterConfig var1) throws ServletException {
        logger.info("AdminFilter start!");
    }

    @Override
    public void destroy() {
        logger.info("AdminFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null || user.getType() != User.ADMIN_TYPE) {
            httpServletResponse.sendRedirect("/index");
            return;
        }

        chain.doFilter(request, response);
    }
}
