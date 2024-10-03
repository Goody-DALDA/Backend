package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Beer;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BeerResponseDto extends AlcoholResponseDto{

    private String enName;
    private String type;
    private String country;
    private String comment;

    @Override
    public String getCategory() {
        return "Beer";
    }

    public static BeerResponseDto of(Beer beer) {
        return BeerResponseDto.builder()
                .id(beer.getId())
                .img(beer.getImg())
                .name(beer.getName())
                .enName(beer.getEnName())
                .type(beer.getType())
                .country(beer.getCountry())
                .comment(beer.getComment())
                .build();
    }
}
