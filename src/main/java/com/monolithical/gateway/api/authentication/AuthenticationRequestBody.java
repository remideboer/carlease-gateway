package com.monolithical.gateway.api.authentication;

public class AuthenticationRequestBody {
    private String username;
    private String password;

    public AuthenticationRequestBody() {
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
