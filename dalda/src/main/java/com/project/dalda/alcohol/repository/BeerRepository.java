package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Long> {
    List<Beer> findByIdIn(List<Long> ids);
}
