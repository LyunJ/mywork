package com.example.mywork.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserDTO {
    private Integer userId;
    private String username;
    private String password;
}
