package com.uasz.gestionsujets.dto;

import com.uasz.gestionsujets.entity.StatutSujet;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SujetResponse {
    private Long id;
    private String titre;
    private String description;
    private Long enseignantId;
    private String enseignantNomComplet;
    private StatutSujet statut;
    private LocalDateTime dateProposition;
    private LocalDateTime dateValidation;
}