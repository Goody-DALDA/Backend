package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Whisky;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class WhiskyResponseDto extends AlcoholResponseDto {

    private String type;
    private String country;
    private String price;
    private String aroma;
    private String taste;
    private String finish;
    private String volume;
    private String abv;

    @Override
    public String getCategory() {
        return "Whisky";
    }

    public static WhiskyResponseDto of(Whisky whisky) {
        return WhiskyResponseDto.builder()
                .name(whisky.getName())
                .id(whisky.getId())
                .img(whisky.getImg())
                .type(whisky.getType())
                .country(whisky.getCountry())
                .price(whisky.getPrice())
                .aroma(whisky.getAroma())
                .taste(whisky.getTaste())
                .finish(whisky.getFinish())
                .volume(whisky.getVolume())
                .abv(whisky.getAbv())
                .build();
    }
}

