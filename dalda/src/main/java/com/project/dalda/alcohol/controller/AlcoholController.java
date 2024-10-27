package com.project.dalda.alcohol.controller;

import com.project.dalda.alcohol.response.AlcoholResponseDto;
import com.project.dalda.alcohol.service.AlcoholService;
import com.project.dalda.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alcohols")
@RequiredArgsConstructor
public class AlcoholController {
    private final AlcoholService alcoholService;

    @GetMapping
    public CommonResponse<?> getAlcohols(@RequestParam(required = false) String category) {
        List<AlcoholResponseDto> alcohols;

        if (category == null || category.isEmpty()) {
            alcohols = alcoholService.getAllAlcohols();
        } else {
            switch (category.toLowerCase()) {
                case "soju":
                    alcohols = alcoholService.getSojuAlcohols();
                    break;
                case "beer":
                    alcohols = alcoholService.getBeerAlcohols();
                    break;
                case "wine":
                    alcohols = alcoholService.getWineAlcohols();
                    break;
                case "sake":
                    alcohols = alcoholService.getSakeAlcohols();
                    break;
                case "traditionalliquor":
                    alcohols = alcoholService.getTraditionalLiquorAlcohols();
                    break;
                case "wisky":
                    alcohols = alcoholService.getWiskyAlcohols();
                    break;
                default:
                    throw new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.");
            }
        }
        return CommonResponse.ok(alcohols, "주류 목록 조회 성공");
    }
}
