package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Wisky;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WiskyRepository extends JpaRepository<Wisky, Long> {
    List<Wisky> findByIdIn(List<Long> ids);
}
