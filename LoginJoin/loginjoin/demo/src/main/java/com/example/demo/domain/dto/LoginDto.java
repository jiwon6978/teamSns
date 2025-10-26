package com.example.demo.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;
    @NotBlank(message = "비밀번호를 입력하세요.")
    private String passWord;
}
