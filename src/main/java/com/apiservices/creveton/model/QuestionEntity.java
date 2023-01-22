package com.apiservices.creveton.model;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questionnaire")
public class QuestionEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    private String q;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String rj;
    private String domaine;
    private Integer level;
    private Integer difficulte;
}
