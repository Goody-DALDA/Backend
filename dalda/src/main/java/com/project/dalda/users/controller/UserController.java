package com.project.dalda.users.controller;
import com.project.dalda.common.response.CommonResponse;
import com.project.dalda.users.config.JwtTokenProvider;
import com.project.dalda.users.entity.User;
import com.project.dalda.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public CommonResponse<Map<String, Object>> loginWithKakao(@RequestParam String email, @RequestParam String nickname, @RequestParam String profileImg) {
        try {
            // isNewUser를 먼저 판단
            boolean isNewUser = userService.isNewUser(email);

            // 로그인 처리
            String accessToken = userService.kakaoLogin(email, nickname, profileImg);

            // 응답 데이터 구성
            Map<String, Object> data = new HashMap<>();
            data.put("accessToken", accessToken);
            data.put("isNewUser", isNewUser);

            return CommonResponse.ok(data, "로그인이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            return CommonResponse.error("로그인 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @GetMapping("/profile")
    public CommonResponse<User> getProfile(@RequestHeader("Authorization") String token) {
        try {
            token = token.replace("Bearer ", "").trim();
            String email = jwtTokenProvider.getEmailFromToken(token);
            User user = userService.getUserProfile(email);
            return CommonResponse.ok(user, "프로필 정보 조회 성공");
        } catch (Exception e) {
            return CommonResponse.error("프로필 조회 중 오류 발생: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public CommonResponse<?> logout(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userService.getUserProfile(email);
        userService.logout(user);
        return CommonResponse.ok(email,"로그아웃 성공");
    }

    @DeleteMapping
    public CommonResponse<?>  deleteUser(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userService.getUserProfile(email);
        userService.deleteAccount(user);
        return CommonResponse.ok(email,"계정 삭제 성공");
    }
}
