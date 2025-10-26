package com.example.demo.config.auth;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService's loadUserByUsername : " + userName);

        Optional<User> userOptional =
                userRepository.findByUserName(userName);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException(userName+" 계정이 존재하지 않습니다");

        //ENTITY -> DTO
        User user = userOptional.get();
        UserDto dto = new UserDto();
        dto.setUserName(user.getUserName());
        dto.setPassWord(user.getPassWord());
        dto.setRole(user.getRole());

        return new PrincipalDetails(dto);
    }
}