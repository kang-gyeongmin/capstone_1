package org.boot.capstone_1.controller;

import jakarta.servlet.http.HttpSession;
import org.boot.capstone_1.dto.*;
import org.boot.capstone_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signupPage";
    }
    @GetMapping("/check-id")
    public ResponseEntity<CheckIdResponse> checkUserId(@RequestParam String userId) {
        boolean exists = userService.isUserIdExists(userId);
        CheckIdResponse response = new CheckIdResponse(!exists, exists ? "userId already exists." : "You can use this userId.");
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // 409
        } else {
            return ResponseEntity.ok(response); // 200
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        // 디버깅을 위한 로그 추가
        System.out.println("Received Password: " + registerRequest.getUserPassword());
        System.out.println("Received Password Confirm: " + registerRequest.getUserPasswordConfirm());

        // pw & pw 확인 일치/불일치 체크
        if (!registerRequest.getUserPassword().equals(registerRequest.getUserPasswordConfirm())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RegisterResponse(false, "Passwords do not match.")); // 400
        }

        // RegisterRequest -> UserDTO 로 변환
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(registerRequest.getUserId());
        userDTO.setUserPassword(registerRequest.getUserPassword());
        userDTO.setUserName(registerRequest.getUserName());

        // id 중복 체크
        if (userService.isUserIdExists(userDTO.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RegisterResponse(false, "userId already exists.")); // 409
        }

        // 회원가입 성공 시
        RegisterResponse response = userService.register(userDTO);
        return ResponseEntity.ok(response); // 200
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "loginPage";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "loginPage";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        String userPassword = loginRequest.getUserPassword();

        boolean isLoginSuccess = userService.login(userId, userPassword);
        // 로그인 성공 시
        if (isLoginSuccess) {
            LoginResponse response = new LoginResponse(true, "Login success.");
            return ResponseEntity.ok(response); // 200
        }
        // 로그인 실패 시
        LoginResponse response = new LoginResponse(false, "Invalid ID or PW.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 401
    }


    @GetMapping("/{userId}/main")
    public ResponseEntity<MainResponse> getUserMain(@PathVariable String userId) {
        UserDTO user = userService.getUserDTOByUserId(userId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MainResponse(false, "User not found", null));
        }

        MainResponse response = new MainResponse(true, "Success", user);
        return ResponseEntity.ok(response);
    }


    // 로그아웃 기능
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 삭제 (로그아웃)
        return "loginPage";
    }


}