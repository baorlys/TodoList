package org.example.todolist.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.todolist.dto.request.SignupRequest;
import org.example.todolist.dto.response.SignupResponse;
import org.example.todolist.service.AuthService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Value("${front-end.url}")
    private String frontEndUrl;
//    @Autowired
//    private AuthService authService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        if(token.getAuthorizedClientRegistrationId().equals("github")) {
//            DefaultOAuth2User user = (DefaultOAuth2User) token.getPrincipal();
//            Map<String,Object> attributes = user.getAttributes();
//            String email = attributes.getOrDefault("email", "").toString();
//            String name = attributes.getOrDefault("name", "").toString();
//            try {
//                SignupResponse createdUser = authService.createUserWithOAuth2(email, name);
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else if(token.getAuthorizedClientRegistrationId().equals("google")) {
//            // do something
//        }
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(frontEndUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
