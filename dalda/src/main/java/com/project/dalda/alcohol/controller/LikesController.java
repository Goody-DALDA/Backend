package com.project.dalda.alcohol.controller;

import com.project.dalda.alcohol.response.LikesResponseDto;
import com.project.dalda.alcohol.service.LikesService;
import com.project.dalda.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/alcohols/likes/")
@RequiredArgsConstructor
public class LikesController {
    // API for alcohol likes

    private final LikesService likesService;

    @PostMapping("{alcoholType}/{alcoholId}/{likeId}")
    public CommonResponse<?> likeAlcohol(@PathVariable String alcoholType, @PathVariable Long alcoholId, @PathVariable Long likeId) {
        Long userId = 1L; // TODO: User ID should be passed from request header or JWT token.
        String status = likesService.likeAlcohol(alcoholId, userId, alcoholType, likeId);
        LikesResponseDto likesResponseDto = LikesResponseDto.of(alcoholId, alcoholType);
        return status.equals("active") ? CommonResponse.ok(likesResponseDto,"좋아요가 설정되었습니다.") : CommonResponse.ok(likesResponseDto,"좋아요가 해제되었습니다.");
    }

    @GetMapping()
    public CommonResponse<?> getList() {
        Long userId = 1L; // TODO: User ID should be passed from request header or JWT token.
        List<Object> list = likesService.getLikeList(userId);
        return CommonResponse.ok(list,"좋아요 리스트를 불러왔습니다.");
    }

}
