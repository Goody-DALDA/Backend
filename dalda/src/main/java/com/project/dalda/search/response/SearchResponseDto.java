package com.project.dalda.search.response;

import com.project.dalda.search.entity.Search;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class SearchResponseDto {

    private Long id;
    private Long alcoholId;
    private String category;
    private String name;

    public static SearchResponseDto of(Search search) {
        return SearchResponseDto.builder()
                .id(search.getId())
                .alcoholId(search.getAlcoholId())
                .category(search.getCategory())
                .name(search.getName())
                .build();
    }
}
