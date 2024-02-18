package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.User;
import com.app.bucksbunny.service.UserService;
import com.app.bucksbunny.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private UserService service;

    @PostMapping("/new")
    public User addNewUser(@RequestBody User user){
        return service.addAdmin(user);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}
