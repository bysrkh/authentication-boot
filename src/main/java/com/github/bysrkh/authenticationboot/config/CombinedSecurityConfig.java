/**
 * @2021, Jogja
 * bysrkh
 * https://github.com/bysrkh
 * Apache-2.0
 */
package com.github.bysrkh.authenticationboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CombinedSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Declare default AuthenticationManager from Spring as a default AuthenticationManager bean in Spring Context
     * It will lead to Principal and Credential Configuration on {@link PrincipalAndCredentialConfig} be utilized
     * @return {@link AuthenticationManager}
     * @throws Exception no infos
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers();
    }
}
