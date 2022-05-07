package com.example.backend.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank(message = "The username can't be blank")
    private String username;

    @NotBlank(message = "The password can't be blank")
    private String password;
}
