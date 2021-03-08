/**
 * @2021, Jogja
 * bysrkh
 * https://github.com/bysrkh
 * Apache-2.0
 */
package com.github.bysrkh.authenticationboot.config;

import com.github.bysrkh.authenticationboot.filter.AuthenticationRequestValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class AuthenticationConfig extends AuthorizationServerConfigurerAdapter {
    private AuthenticationRequestValidationFilter authRequestValidationFilter;
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("r1zk4jul14")
                .secret("r1zk4jul14")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("webclient");
    }

    @Autowired
    public void setAuthRequestValidationFilter(AuthenticationRequestValidationFilter authRequestValidationFilter) {
        this.authRequestValidationFilter = authRequestValidationFilter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    /**
     * Set a default Authentication Manager
     * @param authenticationManager a default {@link AuthenticationManager} configured on {@link CombinedSecurityConfig}.
     */
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
