package com.project.dalda.notice.response;

import com.project.dalda.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class NoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static NoticeResponseDto of(Notice notice) {
        return NoticeResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .build();
    }
}
