package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Sake;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
public class SakeResponseDto extends AlcoholResponseDto{

    private String price;
    private String country;
    private String volume;
    private String aroma;
    private String taste;
    private String finish;
    private String abv;

    @Override
    public String getCategory() {
        return "Sake";
    }

    public static SakeResponseDto of(Sake sake) {
        return SakeResponseDto.builder()
                .id(sake.getId())
                .name(sake.getName())
                .img(sake.getImg())
                .price(sake.getPrice())
                .country(sake.getCountry())
                .volume(sake.getVolume())
                .aroma(sake.getAroma())
                .taste(sake.getTaste())
                .finish(sake.getFinish())
                .abv(sake.getAbv())
                .build();
    }
}
