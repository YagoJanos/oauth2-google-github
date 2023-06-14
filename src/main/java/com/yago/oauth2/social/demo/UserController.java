package com.yago.oauth2.social.demo;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        if (!email.endsWith("@ilegra.com")) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You are not authorized to access this resource");
        }

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", principal.getAttribute("name"));
        userDetails.put("image", principal.getAttribute("picture"));
        return userDetails;
    }
}
