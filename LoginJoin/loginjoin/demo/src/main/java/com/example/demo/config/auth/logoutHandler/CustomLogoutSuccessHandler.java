package com.example.demo.config.auth.logoutHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;


@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    // 로컬서버 로그아웃 이후 추가 처리(ex. 카카오인증서버 연결해제..)
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("CustomLogoutSuccessHandler's onLogoutSuccess invoke..!");

        response.sendRedirect("/");
    }
}