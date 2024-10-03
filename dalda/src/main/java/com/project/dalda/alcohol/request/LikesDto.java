package com.project.dalda.alcohol.request;

import com.project.dalda.alcohol.entity.Likes;
import com.project.dalda.alcohol.response.LikesResponseDto;
import com.project.dalda.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesDto {
    private Long alcoholId;
    private String alcoholType;
    private Users users;
    private String status;

    public Likes toEntity(Long alcoholId, String alcoholType, Users user, String status) {
        return Likes.builder()
                .alcoholId(alcoholId)
                .alcoholType(alcoholType)
                .userId(user)
                .status(status)
                .build();
    }
}
