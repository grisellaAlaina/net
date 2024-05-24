package com.space.netspace.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
    @NotEmpty(message = "name must be not empty")
    @Size(min = 1, max = 30, message = "1-30 name length please")
    private String name;
    @Email
    private String email;
    private String password;
    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
