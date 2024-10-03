package com.project.dalda.alcohol.entity;

import com.project.dalda.users.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", initialValue = 1, allocationSize = 1)
    private Long id;
    private Long alcoholId;
    private String alcoholType;  // 술의 종류 (soju, whiskey, sake 등)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;
    private String status;
    private Date createAt;
    private Date updateAt;

    public void update(String status){
        this.status = status;
    }

}
