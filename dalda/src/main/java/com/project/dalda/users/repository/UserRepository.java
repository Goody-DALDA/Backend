package com.project.dalda.users.repository;

import com.project.dalda.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email); // 이메일 존재 여부 확인 메서드
}
