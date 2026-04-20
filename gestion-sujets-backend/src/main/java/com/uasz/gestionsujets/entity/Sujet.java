package com.uasz.gestionsujets.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sujets")
public class Sujet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Long enseignantId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutSujet statut = StatutSujet.PROPOSE;

    private LocalDate dateProposition;
    private LocalDate dateValidation;

    public Sujet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(Long enseignantId) {
        this.enseignantId = enseignantId;
    }

    public StatutSujet getStatut() {
        return statut;
    }

    public void setStatut(StatutSujet statut) {
        this.statut = statut;
    }

    public LocalDate getDateProposition() {
        return dateProposition;
    }

    public void setDateProposition(LocalDate dateProposition) {
        this.dateProposition = dateProposition;
    }

    public LocalDate getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(LocalDate dateValidation) {
        this.dateValidation = dateValidation;
    }
}