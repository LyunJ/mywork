package com.example.mywork.controller.api;

import com.example.mywork.config.security.filter.JwtAuthenticationProcessingFilter;
import com.example.mywork.config.security.provider.JwtTokenProvider;
import com.example.mywork.model.LoginDTO;
import com.example.mywork.model.TokenDTO;
import com.example.mywork.model.UserDTO;
import com.example.mywork.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authorize(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication.getName());

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization","Bearer " + jwt);
        log.info(jwt);

        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody LoginDTO loginDTO){
        UserDTO user = userService.signup(loginDTO);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
