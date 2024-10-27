package com.project.dalda.search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Search{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int searchCount;

    private Long alcoholId;

    private String category;

    private LocalDateTime lastSearched;

    public void incrementSearchCount() {
        this.searchCount++;
        this.lastSearched = LocalDateTime.now();
    }
}
