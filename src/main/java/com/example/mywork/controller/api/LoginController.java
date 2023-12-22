package com.example.mywork.controller.api;

import com.example.mywork.config.security.filter.JwtAuthenticationProcessingFilter;
import com.example.mywork.config.security.provider.JwtTokenProvider;
import com.example.mywork.model.LoginDTO;
import com.example.mywork.model.TokenDTO;
import com.example.mywork.model.UserDTO;
import com.example.mywork.service.CookieService;
import com.example.mywork.service.LoginService;
import com.example.mywork.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final LoginService loginService;
    private final CookieService cookieService;
    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authorize(@RequestBody LoginDTO loginDTO){

        Authentication authentication = loginService.getAuthentication(loginDTO);
        if (authentication == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String jwt = jwtTokenProvider.createToken(authentication.getName());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer " + jwt);

        return new ResponseEntity<TokenDTO>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity login(HttpServletResponse response, @ModelAttribute("loginDTO") LoginDTO loginDTO){
        Authentication authentication = loginService.getAuthentication(loginDTO);
        if (authentication == null){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/page/login"));

            return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
        }

        String jwt = jwtTokenProvider.createToken(authentication.getName());

        cookieService.setCookie(response,"jwt",jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer " + jwt);
        httpHeaders.setLocation(URI.create("/page/home"));


        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody LoginDTO loginDTO){
        UserDTO user = userService.signup(loginDTO);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
