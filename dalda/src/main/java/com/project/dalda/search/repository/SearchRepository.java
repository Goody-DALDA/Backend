package com.project.dalda.search.repository;

import com.project.dalda.search.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {
    @Query("SELECT s FROM Search s WHERE s.name LIKE %?1% ORDER BY s.searchCount DESC, s.name ASC")
    List<Search> findRecommendations(String input);

    @Query("SELECT s FROM Search s WHERE s.name LIKE %?1% ORDER BY s.category ASC, s.name ASC")
    List<Search> getSearchData(String input);

}
