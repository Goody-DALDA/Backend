package com.project.dalda.users.service;
import com.project.dalda.users.config.JwtTokenProvider;
import com.project.dalda.users.entity.Token;
import com.project.dalda.users.entity.User;
import com.project.dalda.users.repository.TokenRepository;
import com.project.dalda.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String kakaoLogin(String email, String nickname, String profileImg) {
        // 사용자 존재 여부 확인
        User user = userRepository.findByEmail(email).orElse(null);

        // 사용자 없을 경우 새로 생성
        if (user == null) {
            user = User.builder()
                    .email(email)
                    .nickname(nickname)
                    .profileImg(profileImg)
                    .createAt(new Date())
                    .updateAt(new Date())
                    .build();
            userRepository.save(user);
        }

        // 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken();

        // 데이터베이스에 리프레시 토큰 저장/갱신
        Token token = tokenRepository.findByUser(user).orElse(null);
        if (token == null) {
            token = Token.builder()
                    .user(user)
                    .refreshToken(refreshToken)
                    .createAt(new Date())
                    .build();
            tokenRepository.save(token);
        } else {
            token.setRefreshToken(refreshToken);
            tokenRepository.save(token);
        }

        return accessToken; // 안드로이드로 엑세스 토큰 반환
    }

    public User getUserProfile(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void logout(User user) {
        tokenRepository.deleteByUser(user);
    }

    public void deleteAccount(User user) {
        tokenRepository.deleteByUser(user);
        userRepository.delete(user);
    }
}

