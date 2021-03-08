/**
 * @2021, Jogja
 * bysrkh
 * https://github.com/bysrkh
 * Apache-2.0
 */
package com.github.bysrkh.authenticationboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SecurityBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(SecurityBootApplication.class, args);

    }

}
