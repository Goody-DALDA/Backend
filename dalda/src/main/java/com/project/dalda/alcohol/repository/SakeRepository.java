package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Sake;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SakeRepository extends JpaRepository<Sake, Long> {
    List<Sake> findByIdIn(List<Long> ids);
}
