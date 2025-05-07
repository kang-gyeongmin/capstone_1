package org.boot.capstone_1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_eip") // users 테이블이랑 연결
public class UsersEip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_eip_id")
    private Long usersEipId;

    // Users 테이블의 외래키
    @Column(name = "users_id", nullable = false)
    private Long usersId;

    // EIP 테이블의 외래키
    @Column(name = "eip_id", nullable = false)
    private Long eipId;

    // 학습일
    @Column(name = "study_date", nullable = false)
    private LocalDate studyDate;

    // 학습 여부
    @Column(name = "study_status", nullable = false)
    private boolean studyStatus = false;

}