package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findByIdIn(List<Long> ids);
}
