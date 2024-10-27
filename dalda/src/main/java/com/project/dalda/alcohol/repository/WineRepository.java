package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Long> {
    List<Wine> findByIdIn(List<Long> ids);
}
