package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Wisky;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class WiskyResponseDto extends AlcoholResponseDto {

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
        return "Wisky";
    }

    public static WiskyResponseDto of(Wisky wisky) {
        return WiskyResponseDto.builder()
                .name(wisky.getName())
                .id(wisky.getId())
                .img(wisky.getImg())
                .type(wisky.getType())
                .country(wisky.getCountry())
                .price(wisky.getPrice())
                .aroma(wisky.getAroma())
                .taste(wisky.getTaste())
                .finish(wisky.getFinish())
                .volume(wisky.getVolume())
                .abv(wisky.getAbv())
                .build();
    }
}

