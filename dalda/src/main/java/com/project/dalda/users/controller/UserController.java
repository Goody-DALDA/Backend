package com.project.dalda.users.controller;
import com.project.dalda.users.config.JwtTokenProvider;
import com.project.dalda.users.entity.User;
import com.project.dalda.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public String loginWithKakao(@RequestParam String email, @RequestParam String nickname, @RequestParam String profileImg) {
        return userService.kakaoLogin(email, nickname, profileImg);
    }

    @GetMapping("/profile")
    public User getProfile(@RequestHeader("Authorization") String token) {
        System.out.println("token: " + token);
        String email = jwtTokenProvider.getEmailFromToken(token);
        return userService.getUserProfile(email);
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userService.getUserProfile(email);
        userService.logout(user);
        return "Logout successful";
    }

    @DeleteMapping
    public String deleteUser(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userService.getUserProfile(email);
        userService.deleteAccount(user);
        return "Account deleted";
    }
}
