package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Soju;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SojuResponseDto extends AlcoholResponseDto{

    private String price;
    private String volume;
    private String comment;
    private String abv;
    @Override
    public String getCategory() {
        return "Soju";
    }

    public static SojuResponseDto of(Soju soju) {
        return SojuResponseDto.builder()
                .id(soju.getId())
                .img(soju.getImg())
                .name(soju.getName())
                .price(soju.getPrice())
                .volume(soju.getVolume())
                .comment(soju.getComment())
                .abv(soju.getAbv())
                .build();
    }
}
