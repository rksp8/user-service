package com.rksp8.userservice.config.security;


import com.rksp8.userservice.service.user.UserService;
import com.rksp8.userservice.util.CustomOauth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@AllArgsConstructor
public class CustomAuthHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    public CustomAuthHandler() {
        super();
        setDefaultTargetUrl("http://localhost:8080/users/me");
        setAlwaysUseDefaultTargetUrl(true);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        CustomOauth2User oidcUser = (CustomOauth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();

        for (String attribute: attributes.keySet().stream().toList()) {
            System.out.println(attribute);
            System.out.println(attributes.get(attribute));
        }

        String username = (String) attributes.get("name");

        userService.createUser(username);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
