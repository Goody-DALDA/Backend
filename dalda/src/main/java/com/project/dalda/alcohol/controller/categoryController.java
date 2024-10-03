package com.project.dalda.alcohol.controller;

import com.project.dalda.alcohol.response.AlcoholResponseDto;
import com.project.dalda.alcohol.service.CategoryService;
import com.project.dalda.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 경로를 지정하지 않고 클래스에 어노테이션 추가
@RequestMapping("/api/alcohols") // 기본 경로 설정
@RequiredArgsConstructor
public class categoryController {
    private final CategoryService categoryService;

    @GetMapping
    public CommonResponse<?> likeAlcohol(@RequestParam(required = false) String category) {
        List<AlcoholResponseDto> alcohols;

        if (category == null || category.isEmpty()) {
            // 모든 카테고리 조회
            alcohols = categoryService.getAllAlcohols();
        } else {
            // 카테고리별 조회
            switch (category.toLowerCase()) {
                case "soju":
                    alcohols = categoryService.getSojuAlcohols();
                    break;
                case "beer":
                    alcohols = categoryService.getBeerAlcohols();
                    break;
                case "wine":
                    alcohols = categoryService.getWineAlcohols();
                    break;
                case "sake":
                    alcohols = categoryService.getSakeAlcohols();
                    break;
                case "traditionalliquor":
                    alcohols = categoryService.getTraditionalLiquorAlcohols();
                    break;
                case "wisky":
                    alcohols = categoryService.getWiskyAlcohols();
                    break;
                default:
                    throw new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.");
            }
        }
        return CommonResponse.ok(alcohols, "주류 목록 조회 성공");
    }
}
