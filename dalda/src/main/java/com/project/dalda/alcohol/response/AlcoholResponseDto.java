package com.project.dalda.alcohol.response;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class AlcoholResponseDto {
    protected Long id;
    protected String name;
    protected String img;

    // 공통 메소드 정의
    public abstract String getCategory();
}
