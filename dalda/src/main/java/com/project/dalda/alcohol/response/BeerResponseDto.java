package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Beer;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BeerResponseDto extends AlcoholResponseDto{

    private String enName;
    private String type;
    private String country;
    private Double appearance;
    private Double flavor;
    private Double mouthfeel;
    private Double aroma;
    private String abv;

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
                .abv(beer.getAbv())
                .country(beer.getCountry())
                .appearance(beer.getAppearance())
                .flavor(beer.getFlavor())
                .mouthfeel(beer.getMouthfeel())
                .aroma(beer.getAroma())
                .build();
    }
}
