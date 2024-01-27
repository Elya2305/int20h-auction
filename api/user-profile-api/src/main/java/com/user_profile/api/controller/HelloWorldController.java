package com.user_profile.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
public class HelloWorldController {

    @GetMapping("/alive")
    public boolean isAlive() {
        return true;
    }
}
