package com.uasz.gestionsujets.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sujets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sujet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Long enseignantId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatutSujet statut;

    @Column(nullable = false)
    private LocalDateTime dateProposition;

    private LocalDateTime dateValidation;
}