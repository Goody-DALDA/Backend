package com.project.dalda.notice.controller;

import com.project.dalda.common.response.CommonResponse;
import com.project.dalda.notice.entity.Notice;
import com.project.dalda.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    @GetMapping
    public CommonResponse<?> getNotices() {
        List<Notice> notices = noticeService.findAll();
        return CommonResponse.ok(notices, "공지사항 불러오기 성공");
    }
}
