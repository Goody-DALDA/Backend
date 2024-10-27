package com.project.dalda.alcohol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", initialValue = 1, allocationSize = 1)
    private Long id;
    private String img;
    private String name;
    private String enName;
    private String type;
    private String country;
    private String abv;
    private Double appearance;
    private Double flavor;
    private Double mouthfeel;
    private Double aroma;
    private Date createAt;
}
