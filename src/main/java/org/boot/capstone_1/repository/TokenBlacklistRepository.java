package org.boot.capstone_1.repository;

import org.boot.capstone_1.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long>{


    // accessToken으로 존재 여부 확인 (블랙리스트 체크용)
    boolean existsByToken(String token);

    // 특정 시간 이전 만료된 토큰 삭제 (스케줄러용)
    void deleteByExpirationTimeBefore(LocalDateTime time);

}


