package com.project.dalda.alcohol.response;

import com.project.dalda.alcohol.entity.Likes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesResponseDto {

    private Long alcoholId;
    private String alcoholType;
    //private Date create_at;

    public static LikesResponseDto of(Long alcoholId, String alcoholType) {
        return LikesResponseDto.builder()
                .alcoholId(alcoholId)
                .alcoholType(alcoholType)
                .build();
    }

}
