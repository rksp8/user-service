package com.rksp8.userservice.config.context;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;

        setUsername(req);

        try {
            chain.doFilter(request, response);
        } finally {
            UsernameContext.clear();
        }
    }

    private void setUsername(HttpServletRequest req) {
        String username = req.getHeader("x-username");
        if (username != null) {
            UsernameContext.setUsername(username);
        }
    }
}