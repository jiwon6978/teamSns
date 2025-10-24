package com.example.demo.domain.user.service;


import com.example.demo.domain.user.dtos.JoinDto;
import com.example.demo.domain.user.dtos.LoginDto;
import com.example.demo.domain.user.dtos.ProfileUpdateDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    //회원가입
    @Transactional
    public Long joinRegistration(JoinDto dto) throws Exception {
        String encodedPassword = encoder.encode(dto.getPassWord());
        User user = User.builder()
                .id(null)
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .passWord(dto.getPassWord())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        userRepository.save(user);
        return user.getId();
    }

    //로그인(로컬)
    @Transactional
    public User login(LoginDto dto){
        User user = userRepository.findByPhoneNumber((dto.getPhoneNumber()))
                .orElseThrow(() -> new IllegalArgumentException("전화번호를 다시 확인해주세요"));
        if (!encoder.matches(dto.getPassWord(), user.getPassWord())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    //프로필사진 업데이트
    @Transactional
    public void updateProfile(Long Id, ProfileUpdateDto dto) throws IOException {
        var UpdateImg = userRepository.findById(Id).orElseThrow();
    }

}
