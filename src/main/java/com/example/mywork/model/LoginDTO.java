package com.example.mywork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
    private TeamType teamType;
}
