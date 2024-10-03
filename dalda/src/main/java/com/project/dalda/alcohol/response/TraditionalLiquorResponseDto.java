package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.TraditionalLiquor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TraditionalLiquorResponseDto extends AlcoholResponseDto{

    private String type;
    private String ingredients;
    private String abv;
    private String volume;
    private String comment;
    private String pairingFood;
    private String brewery;
    @Override
    public String getCategory() {
        return "TraditionalLiquor";
    }

    public static TraditionalLiquorResponseDto of(TraditionalLiquor traditionalLiquor) {
        return TraditionalLiquorResponseDto.builder()
                .img(traditionalLiquor.getImg())
                .name(traditionalLiquor.getName())
                .id(traditionalLiquor.getId())
                .type(traditionalLiquor.getType())
                .ingredients(traditionalLiquor.getIngredients())
                .abv(traditionalLiquor.getAbv())
                .volume(traditionalLiquor.getVolume())
                .comment(traditionalLiquor.getComment())
                .pairingFood(traditionalLiquor.getPairingFood())
                .brewery(traditionalLiquor.getBrewery())
                .build();
    }
}
