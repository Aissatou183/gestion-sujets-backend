package com.uasz.gestionsujets.dto;

import jakarta.validation.constraints.NotNull;

public class ChoixSujetRequest {

    @NotNull(message = "Le sujet est obligatoire")
    private Long sujetId;

    @NotNull(message = "L'étudiant est obligatoire")
    private Long etudiantId;

    public Long getSujetId() {
        return sujetId;
    }

    public void setSujetId(Long sujetId) {
        this.sujetId = sujetId;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }
}