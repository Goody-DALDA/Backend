package com.project.dalda.search.controller;

import com.project.dalda.alcohol.response.AlcoholResponseDto;
import com.project.dalda.common.response.CommonResponse;
import com.project.dalda.search.response.SearchResponseDto;
import com.project.dalda.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public CommonResponse<?> getSearchBasedRecommendations(@RequestParam String recommendations) {
        Integer limit = 5; // 5개로 제한

        if(recommendations.isEmpty()) throw new IllegalArgumentException("검색어 값은 빈값일 수 없습니다.");
        return CommonResponse.ok(searchService.getSearchBasedRecommendations(recommendations, limit), "5개의 데이터를 가져오는데 성공하였습니다.");
    }

    @GetMapping("/search/alcohols")
    public CommonResponse<?> getSearchDate(@RequestParam Long id, @RequestParam Long alcoholId, @RequestParam String category) {
        Object alcohols;

        switch (category.toLowerCase()) {
            case "soju":
                alcohols = searchService.getSojuAlcohols(alcoholId);
                break;
            case "beer":
                alcohols = searchService.getBeerAlcohols(alcoholId);
                break;
            case "wine":
                alcohols = searchService.getWineAlcohols(alcoholId);
                break;
            case "sake":
                alcohols = searchService.getSakeAlcohols(alcoholId);
                break;
            case "traditional_liquor":
                alcohols = searchService.getTraditionalLiquorAlcohols(alcoholId);
                break;
            case "wisky":
                alcohols = searchService.getWiskyAlcohols(alcoholId);
                break;
            default:
                throw new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.");
        }
        searchService.incrementSearchCount(id);
        return CommonResponse.ok(alcohols, "검색 결과 리턴");
    }

    @GetMapping("/search/list")
    public CommonResponse<?> list(@RequestParam String name) {
        return CommonResponse.ok(searchService.getSearchData(name), "검색 결과 리턴");
    }

    @GetMapping("/alcohols/{alcoholId}/{category}/view")
    public CommonResponse<?> getAlcohols(@PathVariable int alcoholId, @PathVariable String category) {
        searchService.increaseViewCount(alcoholId, category);
        return CommonResponse.ok(null,"조회 수를 증가시켰습니다.");
    }
}
