package com.example.mywork.service;

import com.example.mywork.model.LoginDTO;
import com.example.mywork.model.UserDTO;
import com.example.mywork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO signup(LoginDTO loginDTO){
        if(userRepository.isUserExists(loginDTO.getUsername())){
            throw new RuntimeException("이미 가입된 유저 입니다");
        }

        userRepository.insertUser(loginDTO.getTeamType(),loginDTO.getUsername(), passwordEncoder.encode(loginDTO.getPassword()));

        return userRepository.selectByLoginId(loginDTO.getUsername());
    }
}
