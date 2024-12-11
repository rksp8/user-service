package com.rksp8.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private OAuth2AuthorizedClientService clientService;

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(OAuth2AuthenticationToken authentication) {
        if (authentication != null) {
            clientService.removeAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        }
    }
}
