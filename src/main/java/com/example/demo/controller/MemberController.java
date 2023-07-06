package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@Controller
public class MemberController {
    @PostMapping("/login")
    public String login(@RequestParam String email, String password) {

        return "login success!";
    }

}
