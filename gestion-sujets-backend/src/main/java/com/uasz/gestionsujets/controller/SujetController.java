package com.uasz.gestionsujets.controller;

import com.uasz.gestionsujets.dto.ChoixSujetRequest;
import com.uasz.gestionsujets.dto.SujetRequest;
import com.uasz.gestionsujets.entity.ChoixSujet;
import com.uasz.gestionsujets.entity.Sujet;
import com.uasz.gestionsujets.service.SujetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sujets")
@CrossOrigin(origins = "*")
public class SujetController {

    private final SujetService sujetService;

    public SujetController(SujetService sujetService) {
        this.sujetService = sujetService;
    }

    @PostMapping
    public ResponseEntity<Sujet> proposerSujet(@Valid @RequestBody SujetRequest request) {
        return ResponseEntity.ok(sujetService.proposerSujet(request));
    }

    @GetMapping
    public ResponseEntity<List<Sujet>> listerTousLesSujets() {
        return ResponseEntity.ok(sujetService.listerTousLesSujets());
    }

    @GetMapping("/valides")
    public ResponseEntity<List<Sujet>> listerSujetsValides() {
        return ResponseEntity.ok(sujetService.listerSujetsValides());
    }

    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<Sujet>> listerSujetsParEnseignant(@PathVariable Long enseignantId) {
        return ResponseEntity.ok(sujetService.listerSujetsParEnseignant(enseignantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sujet> getSujetById(@PathVariable Long id) {
        return ResponseEntity.ok(sujetService.getSujetById(id));
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<Sujet> validerSujet(@PathVariable Long id) {
        return ResponseEntity.ok(sujetService.validerSujet(id));
    }

    @PutMapping("/{id}/rejeter")
    public ResponseEntity<Sujet> rejeterSujet(@PathVariable Long id) {
        return ResponseEntity.ok(sujetService.rejeterSujet(id));
    }

    @PostMapping("/choisir")
    public ResponseEntity<ChoixSujet> choisirSujet(@Valid @RequestBody ChoixSujetRequest request) {
        return ResponseEntity.ok(sujetService.choisirSujet(request));
    }
}