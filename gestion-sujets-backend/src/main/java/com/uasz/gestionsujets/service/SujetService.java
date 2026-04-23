package com.uasz.gestionsujets.service;

import com.uasz.gestionsujets.client.UtilisateurClient;
import com.uasz.gestionsujets.dto.AuthenticatedUserDto;
import com.uasz.gestionsujets.dto.ChoixSujetRequest;
import com.uasz.gestionsujets.dto.ChoixSujetResponse;
import com.uasz.gestionsujets.dto.SujetRequest;
import com.uasz.gestionsujets.dto.SujetResponse;
import com.uasz.gestionsujets.dto.UtilisateurResponse;
import com.uasz.gestionsujets.entity.ChoixSujet;
import com.uasz.gestionsujets.entity.StatutSujet;
import com.uasz.gestionsujets.entity.Sujet;
import com.uasz.gestionsujets.exception.BadRequestException;
import com.uasz.gestionsujets.exception.ResourceNotFoundException;
import com.uasz.gestionsujets.repository.ChoixSujetRepository;
import com.uasz.gestionsujets.repository.SujetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SujetService {

    private final SujetRepository sujetRepository;
    private final ChoixSujetRepository choixSujetRepository;
    private final UtilisateurClient utilisateurClient;

    public SujetResponse proposer(SujetRequest request) {
        AuthenticatedUserDto currentUser = getCurrentUser();

        if (!"ENSEIGNANT".equalsIgnoreCase(currentUser.getRole())) {
            throw new BadRequestException("Seul un enseignant peut proposer un sujet");
        }

        UtilisateurResponse enseignant = utilisateurClient.getUtilisateurById(currentUser.getId());

        if (enseignant == null || !"ENSEIGNANT".equalsIgnoreCase(enseignant.getRole())) {
            throw new BadRequestException("Utilisateur connecté invalide");
        }

        Sujet sujet = Sujet.builder()
                .titre(request.getTitre())
                .description(request.getDescription())
                .enseignantId(currentUser.getId())
                .statut(StatutSujet.PROPOSE)
                .dateProposition(LocalDateTime.now())
                .build();

        sujet = sujetRepository.save(sujet);
        return mapToSujetResponse(sujet);
    }

    public List<SujetResponse> findAll() {
        return sujetRepository.findAll()
                .stream()
                .map(this::mapToSujetResponse)
                .toList();
    }

    public List<SujetResponse> findDisponiblesPourChoix() {
        return sujetRepository.findByStatut(StatutSujet.PROPOSE)
                .stream()
                .map(this::mapToSujetResponse)
                .toList();
    }

    public SujetResponse findById(Long id) {
        return mapToSujetResponse(getSujetOrThrow(id));
    }

    public List<SujetResponse> findByEnseignantId(Long enseignantId) {
        return sujetRepository.findByEnseignantId(enseignantId)
                .stream()
                .map(this::mapToSujetResponse)
                .toList();
    }

    public List<SujetResponse> findMesSujets() {
        AuthenticatedUserDto currentUser = getCurrentUser();

        if (!"ENSEIGNANT".equalsIgnoreCase(currentUser.getRole())) {
            throw new BadRequestException("Seul un enseignant peut consulter ses sujets");
        }

        return sujetRepository.findByEnseignantId(currentUser.getId())
                .stream()
                .map(this::mapToSujetResponse)
                .toList();
    }

    public SujetResponse valider(Long id) {
        Sujet sujet = getSujetOrThrow(id);

        if (sujet.getStatut() != StatutSujet.EN_ATTENTE_VALIDATION) {
            throw new BadRequestException("Seul un sujet choisi en attente peut être validé");
        }

        sujet.setStatut(StatutSujet.VALIDE);
        sujet.setDateValidation(LocalDateTime.now());

        sujet = sujetRepository.save(sujet);
        return mapToSujetResponse(sujet);
    }

    public SujetResponse rejeter(Long id) {
        Sujet sujet = getSujetOrThrow(id);

        if (sujet.getStatut() != StatutSujet.EN_ATTENTE_VALIDATION) {
            throw new BadRequestException("Seul un sujet choisi en attente peut être rejeté");
        }

        sujet.setStatut(StatutSujet.REJETE);
        sujet.setDateValidation(LocalDateTime.now());

        sujet = sujetRepository.save(sujet);
        return mapToSujetResponse(sujet);
    }

    public ChoixSujetResponse choisir(ChoixSujetRequest request) {
        AuthenticatedUserDto currentUser = getCurrentUser();

        if (!"ETUDIANT".equalsIgnoreCase(currentUser.getRole())) {
            throw new BadRequestException("Seul un étudiant peut choisir un sujet");
        }

        Sujet sujet = getSujetOrThrow(request.getSujetId());

        if (sujet.getStatut() != StatutSujet.PROPOSE) {
            throw new BadRequestException("Ce sujet n'est plus disponible pour être choisi");
        }

        if (choixSujetRepository.existsBySujetId(request.getSujetId())) {
            throw new BadRequestException("Ce sujet a déjà été choisi");
        }

        if (choixSujetRepository.existsByEtudiantId(currentUser.getId())) {
            throw new BadRequestException("Cet étudiant a déjà choisi un sujet");
        }

        UtilisateurResponse etudiant = utilisateurClient.getUtilisateurById(currentUser.getId());

        if (etudiant == null || !"ETUDIANT".equalsIgnoreCase(etudiant.getRole())) {
            throw new BadRequestException("Utilisateur connecté invalide");
        }

        ChoixSujet choix = ChoixSujet.builder()
                .sujetId(request.getSujetId())
                .etudiantId(currentUser.getId())
                .dateChoix(LocalDateTime.now())
                .build();

        choix = choixSujetRepository.save(choix);

        sujet.setStatut(StatutSujet.EN_ATTENTE_VALIDATION);
        sujetRepository.save(sujet);

        return ChoixSujetResponse.builder()
                .id(choix.getId())
                .sujetId(choix.getSujetId())
                .sujetTitre(sujet.getTitre())
                .etudiantId(choix.getEtudiantId())
                .etudiantNomComplet(etudiant.getPrenom() + " " + etudiant.getNom())
                .dateChoix(choix.getDateChoix())
                .build();
    }

    private AuthenticatedUserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new BadRequestException("Utilisateur non authentifié");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof AuthenticatedUserDto user)) {
            throw new BadRequestException("Informations utilisateur invalides");
        }

        return user;
    }

    private Sujet getSujetOrThrow(Long id) {
        return sujetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sujet introuvable avec l'id : " + id));
    }

    private SujetResponse mapToSujetResponse(Sujet sujet) {
        String enseignantNomComplet = null;

        try {
            UtilisateurResponse enseignant = utilisateurClient.getUtilisateurById(sujet.getEnseignantId());
            if (enseignant != null) {
                enseignantNomComplet = enseignant.getPrenom() + " " + enseignant.getNom();
            }
        } catch (Exception ignored) {
        }

        return SujetResponse.builder()
                .id(sujet.getId())
                .titre(sujet.getTitre())
                .description(sujet.getDescription())
                .enseignantId(sujet.getEnseignantId())
                .enseignantNomComplet(enseignantNomComplet)
                .statut(sujet.getStatut())
                .dateProposition(sujet.getDateProposition())
                .dateValidation(sujet.getDateValidation())
                .build();
    }
}