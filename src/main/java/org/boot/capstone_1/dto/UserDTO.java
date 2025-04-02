package org.boot.capstone_1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String userId;
    private String userPassword;
    private String userName;
    private String refreshToken;

}