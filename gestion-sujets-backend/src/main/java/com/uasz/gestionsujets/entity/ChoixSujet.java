package com.uasz.gestionsujets.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "choix_sujets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "sujetId"),
                @UniqueConstraint(columnNames = "etudiantId")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChoixSujet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sujetId;

    @Column(nullable = false)
    private Long etudiantId;

    @Column(nullable = false)
    private LocalDateTime dateChoix;
}