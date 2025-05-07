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
@Table(name = "users_cssd") // users 테이블이랑 연결
public class UsersCssd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_cssd_id")
    private Long usersCssdId;

    // Users 테이블의 외래키
    @Column(name = "users_id", nullable = false)
    private Long usersId;

    // CSSD 테이블의 외래키
    @Column(name = "cssd_id", nullable = false)
    private Long cssdId;

    // 학습일
    @Column(name = "study_date", nullable = false)
    private LocalDate studyDate;

    // 학습 여부
    @Column(name = "study_status", nullable = false)
    private boolean studyStatus = false;

}