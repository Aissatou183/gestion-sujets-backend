package com.uasz.gestionsujets.service;

import com.uasz.gestionsujets.dto.ChoixSujetRequest;
import com.uasz.gestionsujets.dto.SujetRequest;
import com.uasz.gestionsujets.entity.ChoixSujet;
import com.uasz.gestionsujets.entity.StatutSujet;
import com.uasz.gestionsujets.entity.Sujet;
import com.uasz.gestionsujets.exception.ResourceNotFoundException;
import com.uasz.gestionsujets.repository.ChoixSujetRepository;
import com.uasz.gestionsujets.repository.SujetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SujetService {

    private final SujetRepository sujetRepository;
    private final ChoixSujetRepository choixSujetRepository;

    public SujetService(SujetRepository sujetRepository, ChoixSujetRepository choixSujetRepository) {
        this.sujetRepository = sujetRepository;
        this.choixSujetRepository = choixSujetRepository;
    }

    public Sujet proposerSujet(SujetRequest request) {
        Sujet sujet = new Sujet();
        sujet.setTitre(request.getTitre());
        sujet.setDescription(request.getDescription());
        sujet.setEnseignantId(request.getEnseignantId());
        sujet.setStatut(StatutSujet.PROPOSE);
        sujet.setDateProposition(LocalDate.now());
        sujet.setDateValidation(null);

        return sujetRepository.save(sujet);
    }

    public List<Sujet> listerTousLesSujets() {
        return sujetRepository.findAll();
    }

    public List<Sujet> listerSujetsValides() {
        return sujetRepository.findByStatut(StatutSujet.VALIDE);
    }

    public List<Sujet> listerSujetsParEnseignant(Long enseignantId) {
        return sujetRepository.findByEnseignantId(enseignantId);
    }

    public Sujet getSujetById(Long id) {
        return sujetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sujet introuvable avec l'identifiant : " + id));
    }

    public Sujet validerSujet(Long id) {
        Sujet sujet = getSujetById(id);

        if (sujet.getStatut() == StatutSujet.CHOISI) {
            throw new IllegalArgumentException("Impossible de valider un sujet déjà choisi.");
        }

        if (sujet.getStatut() == StatutSujet.VALIDE) {
            throw new IllegalArgumentException("Ce sujet est déjà validé.");
        }

        sujet.setStatut(StatutSujet.VALIDE);
        sujet.setDateValidation(LocalDate.now());

        return sujetRepository.save(sujet);
    }

    public Sujet rejeterSujet(Long id) {
        Sujet sujet = getSujetById(id);

        if (sujet.getStatut() == StatutSujet.CHOISI) {
            throw new IllegalArgumentException("Impossible de rejeter un sujet déjà choisi.");
        }

        if (sujet.getStatut() == StatutSujet.REJETE) {
            throw new IllegalArgumentException("Ce sujet est déjà rejeté.");
        }

        sujet.setStatut(StatutSujet.REJETE);
        sujet.setDateValidation(null);

        return sujetRepository.save(sujet);
    }

    public ChoixSujet choisirSujet(ChoixSujetRequest request) {
        Sujet sujet = getSujetById(request.getSujetId());

        if (sujet.getStatut() != StatutSujet.VALIDE) {
            throw new IllegalArgumentException("Seuls les sujets validés peuvent être choisis.");
        }

        if (choixSujetRepository.existsBySujetId(request.getSujetId())) {
            throw new IllegalArgumentException("Ce sujet a déjà été choisi.");
        }

        if (choixSujetRepository.existsByEtudiantId(request.getEtudiantId())) {
            throw new IllegalArgumentException("Cet étudiant a déjà choisi un sujet.");
        }

        sujet.setStatut(StatutSujet.CHOISI);
        sujetRepository.save(sujet);

        ChoixSujet choixSujet = new ChoixSujet();
        choixSujet.setSujetId(request.getSujetId());
        choixSujet.setEtudiantId(request.getEtudiantId());
        choixSujet.setDateChoix(LocalDate.now());

        return choixSujetRepository.save(choixSujet);
    }
}