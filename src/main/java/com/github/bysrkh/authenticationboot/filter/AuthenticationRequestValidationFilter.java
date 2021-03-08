/**
 * @2021, Jogja
 * bysrkh
 * Apache-2.0
 */
package com.github.bysrkh.authenticationboot.filter;

import com.github.bysrkh.authenticationboot.dto.SubmitLoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@Component
public class AuthenticationRequestValidationFilter extends OncePerRequestFilter {
    private Validator validator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var submitLoginRequest = toSubmitLoginRequest(request);
        var errors = new BeanPropertyBindingResult(submitLoginRequest, SubmitLoginRequest.class.getName());

        validator.validate(submitLoginRequest, errors);
        if (errors.hasErrors()) {
            generateErrorMessage(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !StringUtils.contains("oauth/token", request.getRequestURI());
    }

    private static SubmitLoginRequest toSubmitLoginRequest(HttpServletRequest request) {
        var username = request.getParameter("password");
        var password = request.getParameter("username");

        return new SubmitLoginRequest(username, password);
    }

    private static void generateErrorMessage(HttpServletResponse response) throws IOException {
        response.setStatus(SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.getWriter().write("{\"errors\": {\"fields\": {\"username\": [\"Please provide correct username and password\"], \"password\": [\"Please provide correct username and password\"]}}}");
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
