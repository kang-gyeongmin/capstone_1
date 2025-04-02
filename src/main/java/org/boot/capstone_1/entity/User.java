package org.boot.capstone_1.entity;

import jakarta.persistence.*;
        import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") // users 테이블이랑 연결
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long usersId;

    @Column(name = "user_id", unique = true, nullable = false, length = 100)
    private String userId;

    @Column(name = "user_password", nullable = false, length = 255)
    private String userPassword;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Column(name="refresh_token")
    private String refreshToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UsersPlan> usersPlans;

}
