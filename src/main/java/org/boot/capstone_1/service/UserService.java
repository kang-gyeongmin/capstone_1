package org.boot.capstone_1.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.dto.UserDTO;
import org.boot.capstone_1.dto.RegisterResponse;
import org.boot.capstone_1.entity.User;
import org.boot.capstone_1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession session;
    private final PasswordEncoder passwordEncoder;

    // 아이디 중복 확인 기능
    public boolean isUserIdExists(String userId) {
        return userRepository.existsByUserId(userId);
    }

    // 회원가입 기능
    public RegisterResponse register(UserDTO userDTO) {
        // 아이디 중복 확인
        if (userRepository.existsByUserId(userDTO.getUserId())) {
            return new RegisterResponse(false, "userId already exists.");
        }

        // dto -> entity 로 변환
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword())); // 비밀번호 암호화
        user.setUserName(userDTO.getUserName());

        userRepository.save(user);

        return new RegisterResponse(true, "User created.");
    }

    // 로그인 기능
    public boolean login(String userId, String userPassword) {
        Optional<User> userOptional = userRepository.findByUserId(userId);

        // 사용자 없음
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        // 비밀번호 불일치
        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            return false;
        }

        session.setAttribute("currentUser", user); // 세션 저장
        return true;
    }

    public UserDTO getUserDTOByUserId(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);

        // Optional을 UserDTO로 변환
        return userOptional.map(user -> new UserDTO(
                user.getUserId(),
                user.getUserPassword(),
                user.getUserName()
        )).orElse(null); // 사용자가 없으면 null 반환
    }

    // 로그아웃 기능
    public void logout() {
        session.invalidate(); // 세션 종료
    }

}