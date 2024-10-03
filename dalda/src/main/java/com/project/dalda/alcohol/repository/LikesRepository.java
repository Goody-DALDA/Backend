package com.project.dalda.alcohol.repository;

import com.project.dalda.alcohol.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByUserId_IdAndStatus(Long userId, String status);

}
