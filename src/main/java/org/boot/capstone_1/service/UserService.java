package org.boot.capstone_1.service;

import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.dto.UserDTO;
import org.boot.capstone_1.dto.login.RegisterResponse;
import org.boot.capstone_1.entity.User;
import org.boot.capstone_1.repository.TokenBlacklistRepository;
import org.boot.capstone_1.repository.UserRepository;
import org.boot.capstone_1.security.JwtDTO;
import org.boot.capstone_1.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final TokenBlacklistRepository tokenBlacklistRepository;

    // 아이디 중복 확인 기능
    public boolean isUserIdExists(String userId) {
        return userRepository.existsByUserId(userId);
    }

    // 회원가입 기능
    public RegisterResponse register(UserDTO userDTO) {
        // 아이디 중복 확인
        if (userRepository.existsByUserId(userDTO.getUserId())) {
            return new RegisterResponse(false, "userId already exists.", null, null);
        }

        // dto -> entity 로 변환
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword())); // 비밀번호 암호화
        user.setUserName(userDTO.getUserName());

        userRepository.save(user);

        // JwtDTO 객체에서 accessToken 추출
        JwtDTO jwtDTO = jwtUtil.generateToken(userDTO.getUserId());
        String accessToken = jwtDTO.getAccessToken();  // JwtDTO에서 accessToken을 추출
        String refreshToken = jwtDTO.getRefreshToken();

        return new RegisterResponse(true, "User created.", accessToken, refreshToken);
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

        return true;
    }

    //userId로 usersId 찾기
    public Long getUsersIdFromUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않음"));

        return user.getUsersId(); // users_id 값 리턴
    }

    // 로그인 && 회원가입 시 RefreshToken 저장
    public void updateRefreshToken(String userId, String refreshToken) {
        // 'Bearer '를 제거한 토큰을 저장
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        if (!userRepository.existsByUserId(userId)) {
            throw new RuntimeException("userId not found when updating refresh token");
        }

        userRepository.updateRefreshTokenByUserId(userId, refreshToken);
    }

    // 사용자 ID로 RefreshToken 가져오기
    public Optional<String> getRefreshTokenByUserId(String userId) {
        return userRepository.findRefreshTokenByUserId(userId);
    }


    public boolean isBlacklisted(String accessToken) {
        return tokenBlacklistRepository.existsByToken(accessToken);
    }


    public boolean deleteUser(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);

        // 사용자 없음
        if (userOptional.isEmpty()) {
            return false;
        }

        // 사용자 존재 시 삭제
        userRepository.delete(userOptional.get());
        return true;
    }


}