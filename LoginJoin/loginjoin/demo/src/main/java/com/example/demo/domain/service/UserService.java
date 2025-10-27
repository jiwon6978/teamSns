package com.example.demo.domain.service;

import com.example.demo.domain.dto.JoinDto;
import com.example.demo.domain.dto.ProfileUpdateDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    //회원가입
    @Transactional
    public Long joinRegistration(JoinDto dto) throws Exception {
        String encodedPassword = encoder.encode(dto.getPassWord());
        User user = User.builder()
                .id(null)
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .passWord(encodedPassword)
                .phoneNumber(dto.getPhoneNumber())
                .role("ROLE_USER")
                .build();
        userRepository.save(user);
        return user.getId();
    }
    //프로필사진 업데이트
    @Transactional
    public void updateProfile(Long Id, ProfileUpdateDto dto) throws IOException {
        var UpdateImg = userRepository.findById(Id).orElseThrow();
    }

}
