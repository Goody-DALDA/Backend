package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Beer;
import com.project.dalda.alcohol.entity.Soju;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SojuRepository extends JpaRepository<Soju, Long> {
    List<Soju> findByIdIn(List<Long> ids);
}
