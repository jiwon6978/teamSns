package com.example.demo.domain.user.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String passWord;
    private String role;

    public UserDto(String username,String password,String role){
        this.userName = username;
        this.passWord = password;
        this.role = role;
    }

    //OAuth2 Client Info
    private String provider;
    private String providerId;
}