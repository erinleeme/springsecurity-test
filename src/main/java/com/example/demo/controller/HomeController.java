package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HomeController {

    /*로그인 페이지*/
    @GetMapping("/login")
    public String home() {
        return "This is Login Page.";
    }

    /*메인 페이지*/
    @GetMapping("/")
    public String welcome() {
        return "This is Main Page. Welcome!";
    }
}
