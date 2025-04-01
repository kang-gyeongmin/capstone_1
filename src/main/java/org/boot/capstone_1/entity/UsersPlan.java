package org.boot.capstone_1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_plan") // users 테이블이랑 연결
public class UsersPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_plan_id", unique = true, nullable = false, length = 10)
    private Integer usersPlanId;

    @ManyToOne
    @JoinColumn(name="users_id", nullable=false)
    private User user;

    @Column(name = "study_field", nullable = false, length = 20)
    private String studyField;

    @Column(name = "study_period", nullable = false)
    private Integer studyPeriod;

    @Column(name = "study_day", nullable = false)
    private Integer studyDay;

    @Column(name = "major_non_major", nullable = false, length = 20)
    private String majorNonMajor;

}
