package com.example.demo.DTO;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String fullName;
    private String password;
    private String instrument;
    private Long role;
}
