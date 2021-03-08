/**
 * @2021, Jogja
 * bysrkh
 * https://github.com/bysrkh
 * Apache-2.0
 */
package com.github.bysrkh.authenticationboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class PrincipalAndCredentialConfig {

    @Bean @Autowired
    public UserDetailsService userDetailsService(DataSource dataSource) {
        final var findUser = "SELECT username, password, 1 as enabled " +
                "FROM public.user" +
                " WHERE username = ?";
        final var findAuthority = "SELECT u.username as username, r.role as authority" +
                " FROM public.user_role ur" +
                " INNER JOIN role r" +
                " ON ur.role_id = r.id" +
                " INNER JOIN public.user u" +
                " ON ur.user_id = u.id" +
                " WHERE username = ?";

        var userDetailsService = new JdbcUserDetailsManager(dataSource);
        userDetailsService.setUsersByUsernameQuery(findUser);
        userDetailsService.setAuthoritiesByUsernameQuery(findAuthority);

        return userDetailsService;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

}
