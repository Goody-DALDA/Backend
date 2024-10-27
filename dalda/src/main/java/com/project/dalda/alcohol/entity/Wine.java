package com.project.dalda.alcohol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", initialValue = 1, allocationSize = 1)
    private Long id;
    private String name;
    private String enName;
    private String type;
    private String country;
    private String volume;
    private String winery;
    private String ingredients;
    private String pairingFood;
    private Long sugar;
    private Long acid;
    private Long body;
    private String comment;
    private String img;
    private Date create_at;
}
