package com.uasz.gestionsujets.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "choix_sujets")
public class ChoixSujet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sujetId;

    @Column(nullable = false, unique = true)
    private Long etudiantId;

    @Column(nullable = false, unique = true)
    private Long sujetUniqueId;

    private LocalDate dateChoix;

    public ChoixSujet() {
    }

    public Long getId() {
        return id;
    }

    public Long getSujetId() {
        return sujetId;
    }

    public void setSujetId(Long sujetId) {
        this.sujetId = sujetId;
        this.sujetUniqueId = sujetId;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public Long getSujetUniqueId() {
        return sujetUniqueId;
    }

    public void setSujetUniqueId(Long sujetUniqueId) {
        this.sujetUniqueId = sujetUniqueId;
    }

    public LocalDate getDateChoix() {
        return dateChoix;
    }

    public void setDateChoix(LocalDate dateChoix) {
        this.dateChoix = dateChoix;
    }
}