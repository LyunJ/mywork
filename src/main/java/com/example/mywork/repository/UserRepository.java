package com.example.mywork.repository;

import com.example.mywork.config.security.custom.CustomUserDetails;
import com.example.mywork.model.UserDTO;
import com.example.mywork.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserMapper userMapper;

    public UserDTO selectById(Integer userId){
        return userMapper.selectById(userId);
    }

    public UserDTO selectByLoginId(String userLoginId){
        return userMapper.selectByLoginId(userLoginId);
    }

    public boolean isUserExists(String username){
        return userMapper.isUserExistsByName(username) == 1;
    }

    public CustomUserDetails selectByLoginIdForSecurity(String userLoginId){
        return userMapper.selectByLoginIdForSecurity(userLoginId);
    }

    public void insertUser(String username, String password){
        userMapper.insertUser(username,password);
    }
}
