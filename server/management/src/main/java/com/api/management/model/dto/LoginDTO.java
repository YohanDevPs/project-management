package com.api.management.model.dto;

public class LoginDTO {

    private String password;
    private String email;

    public LoginDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
