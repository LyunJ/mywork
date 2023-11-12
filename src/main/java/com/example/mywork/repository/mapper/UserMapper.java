package com.example.mywork.repository.mapper;

import com.example.mywork.config.security.custom.CustomUserDetails;
import com.example.mywork.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO selectById(Integer userId);

    UserDTO selectByLoginId(String userLoginId);

    CustomUserDetails selectByLoginIdForSecurity(String userLoginId);
}
