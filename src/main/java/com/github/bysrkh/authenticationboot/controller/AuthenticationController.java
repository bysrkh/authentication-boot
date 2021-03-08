/**
 * @2021, Jogja
 * bysrkh
 * https://github.com/bysrkh
 * Apache-2.0
 */
package com.github.bysrkh.authenticationboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuthenticationController {

    @GetMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
    public Map<String, Object> user(Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getAuthorities());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(
                user.getAuthorities())
        );

        return userInfo;
    }
}
