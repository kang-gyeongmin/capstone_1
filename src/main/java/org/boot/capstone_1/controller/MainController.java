package org.boot.capstone_1.controller;

import org.boot.capstone_1.dto.MainResponse;
import org.boot.capstone_1.dto.UserDTO;
import org.boot.capstone_1.entity.User;
import jakarta.servlet.http.HttpSession;
import org.boot.capstone_1.repository.UserRepository;
import org.boot.capstone_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // 메인 페이지로 이동
    @GetMapping("/mainPage")
    public String mainPage(Model model, HttpSession session) {
        // 세션에서 로그인한 사용자 정보 가져오기
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {  // 로그인된 사용자일 경우
            model.addAttribute("userId", loggedInUser.getUserId());
            model.addAttribute("userName", loggedInUser.getUserName());
        } else {  // 로그인되지 않은 경우
            return "loginPage";
        }
        return "mainPage";
    }

}