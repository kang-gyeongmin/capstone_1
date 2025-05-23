package org.boot.capstone_1.security;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class JwtDTO {

    private String grantType;
    private final String accessToken;
    private final String refreshToken;

}