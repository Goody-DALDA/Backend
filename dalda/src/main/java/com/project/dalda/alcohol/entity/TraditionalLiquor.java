package com.project.dalda.alcohol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TraditionalLiquor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", initialValue = 1, allocationSize = 1)
    private Long id;
    private String name;
    private String type;
    private String ingredients;
    private String abv;
    private String volume;
    private String comment;
    private String img;
    private String pairingFood;
    private String brewery;
    private Date create_at;
}
