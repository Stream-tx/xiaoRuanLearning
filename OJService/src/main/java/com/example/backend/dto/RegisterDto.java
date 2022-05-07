package com.example.backend.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {
    @NotBlank(message = "The username can't be blank")
    private String username;

    @NotBlank(message = "The password can't be blank")
    private String password;

    @NotBlank(message = "The email can't be blank")
    @Email(message = "The format of email isn't correct")
    private String email;

    @NotBlank(message = "The university can't be blank")
    private String university;

    private String age;
}
