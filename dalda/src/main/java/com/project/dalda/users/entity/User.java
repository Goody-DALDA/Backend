package com.project.dalda.users.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // 카카오 이메일

    private String nickname; // 카카오 닉네임
    private String profileImg; // 카카오 프로필 이미지
    private String status; // 유저 상태
    private String refreshToken; // JWT 리프레시 토큰

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
}