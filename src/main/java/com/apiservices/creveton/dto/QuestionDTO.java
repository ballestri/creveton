package com.apiservices.creveton.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class QuestionDTO {
    @NotNull
    private Integer numero;
    @NotNull
    private String q;
    @NotNull
    private String r1;
    @NotNull
    private String r2;
    @NotNull
    private String r3;
    @NotNull
    private String r4;
    @NotNull
    private String rj;
    @NotNull
    private String domaine;
    @NotNull
    private Integer level;
    @NotNull
    private Integer difficulte;
}
