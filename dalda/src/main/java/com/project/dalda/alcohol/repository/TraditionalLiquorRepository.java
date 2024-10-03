package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Beer;
import com.project.dalda.alcohol.entity.TraditionalLiquor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraditionalLiquorRepository extends JpaRepository<TraditionalLiquor, Long> {
    List<TraditionalLiquor> findByIdIn(List<Long> ids);
}
