/**
 * @2021, Jogja
 * bysrkh
 * https://github.com/bysrkh
 * Apache-2.0
 */
package com.github.bysrkh.authenticationboot.dto;

import javax.validation.constraints.NotEmpty;

public class SubmitLoginRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public SubmitLoginRequest() {
    }

    public SubmitLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
