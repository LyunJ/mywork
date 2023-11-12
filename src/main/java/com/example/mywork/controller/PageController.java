package com.example.mywork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {
    @GetMapping("/page/home")
    public String home(Model model) {
        return "page/home";
    }
}
