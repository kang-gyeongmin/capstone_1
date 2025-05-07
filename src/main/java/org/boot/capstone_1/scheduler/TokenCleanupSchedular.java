package org.boot.capstone_1.scheduler;

import lombok.RequiredArgsConstructor;
import org.boot.capstone_1.repository.TokenBlacklistRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenCleanupSchedular {


    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Scheduled(cron = "0 0 * * * *") // 매 시간마다
    public void cleanUpExpiredTokens() {
        tokenBlacklistRepository.deleteByExpirationTimeBefore(LocalDateTime.now());
        System.out.println("블랙리스트에서 만료된 토큰 정리됨!");
    }
}
