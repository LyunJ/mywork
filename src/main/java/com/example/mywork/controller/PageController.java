package com.example.mywork.controller;

import com.example.mywork.model.LoginDTO;
import com.example.mywork.model.TeamType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class PageController {
    @GetMapping("/page/home")
    public String home(Model model) {
        return "page/home";
    }

    @GetMapping("/page/login")
    public String login(Model model, LoginDTO loginDTO) {
        model.addAttribute("teamList", TeamType.values());
        model.addAttribute("loginDTO",loginDTO);
        return "page/login";
    }
}
