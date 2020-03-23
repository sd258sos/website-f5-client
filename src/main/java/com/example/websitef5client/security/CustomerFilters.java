package com.example.websitef5client.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author He
 */
@Configuration
public class CustomerFilters {

    @Value("${cors.allowedUrl:http://127.0.0.1:8082}")
    private String allowedUrl;

    class SimpleCorsFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) {
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            if ("options".equalsIgnoreCase(request.getMethod())) {
                response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, accept, content-type, Authorization");
            }
            response.setHeader("Access-Control-Allow-Origin", allowedUrl);
            response.setHeader("Access-Control-Allow-Credentials", "true");

            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void destroy() {
        }
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new SimpleCorsFilter());
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
