package com.project.dalda.notice.repository;

import com.project.dalda.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository  extends JpaRepository<Notice, Long> {
}
