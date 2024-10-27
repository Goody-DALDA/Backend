package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Wine;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class WineResponseDto extends AlcoholResponseDto{

    private String enName;
    private String type;
    private String country;
    private String volume;
    private String winery;
    private String ingredients;
    private String pairingFood;
    private Long sugar;
    private Long acid;
    private Long body;
    private String comment;
    @Override
    public String getCategory() {
        return "Wine";
    }

    public static WineResponseDto of(Wine wine) {
        return WineResponseDto.builder()
                .name(wine.getName())
                .id(wine.getId())
                .img(wine.getImg())
                .enName(wine.getEnName())
                .type(wine.getType())
                .country(wine.getCountry())
                .volume(wine.getVolume())
                .winery(wine.getWinery())
                .ingredients(wine.getIngredients())
                .pairingFood(wine.getPairingFood())
                .sugar(wine.getSugar())
                .acid(wine.getAcid())
                .body(wine.getBody())
                .comment(wine.getComment())
                .build();
    }
}
