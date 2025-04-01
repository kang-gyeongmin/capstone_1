package org.boot.capstone_1.controller;

import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.entity.User;
import jakarta.servlet.http.HttpSession;
import org.boot.capstone_1.repository.UserRepository;
import org.boot.capstone_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class PlanController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/add-plan")

}