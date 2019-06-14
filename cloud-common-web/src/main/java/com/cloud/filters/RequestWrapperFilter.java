package com.cloud.filters;


import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 */
public class RequestWrapperFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            if (HttpMethod.POST.matches(httpServletRequest.getMethod().toUpperCase()) &&
                    httpServletRequest.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
                ServletRequest requestWrapper = new MultiReadHttpServletRequestWrapper((HttpServletRequest) servletRequest);
                filterChain.doFilter(requestWrapper, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
