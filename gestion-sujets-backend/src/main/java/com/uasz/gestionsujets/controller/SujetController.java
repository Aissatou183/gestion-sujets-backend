package com.uasz.gestionsujets.controller;

import com.uasz.gestionsujets.dto.ChoixSujetRequest;
import com.uasz.gestionsujets.dto.ChoixSujetResponse;
import com.uasz.gestionsujets.dto.SujetRequest;
import com.uasz.gestionsujets.dto.SujetResponse;
import com.uasz.gestionsujets.service.SujetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sujets")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SujetController {

    private final SujetService sujetService;

    @PostMapping
    public ResponseEntity<SujetResponse> proposer(@Valid @RequestBody SujetRequest request) {
        return ResponseEntity.ok(sujetService.proposer(request));
    }

    @GetMapping
    public ResponseEntity<List<SujetResponse>> findAll() {
        return ResponseEntity.ok(sujetService.findAll());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<SujetResponse>> findDisponiblesPourChoix() {
        return ResponseEntity.ok(sujetService.findDisponiblesPourChoix());
    }

    @GetMapping("/mes-sujets")
    public ResponseEntity<List<SujetResponse>> findMesSujets() {
        return ResponseEntity.ok(sujetService.findMesSujets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SujetResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(sujetService.findById(id));
    }

    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<SujetResponse>> findByEnseignantId(@PathVariable Long enseignantId) {
        return ResponseEntity.ok(sujetService.findByEnseignantId(enseignantId));
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<SujetResponse> valider(@PathVariable Long id) {
        return ResponseEntity.ok(sujetService.valider(id));
    }

    @PutMapping("/{id}/rejeter")
    public ResponseEntity<SujetResponse> rejeter(@PathVariable Long id) {
        return ResponseEntity.ok(sujetService.rejeter(id));
    }

    @PostMapping("/choisir")
    public ResponseEntity<ChoixSujetResponse> choisir(@Valid @RequestBody ChoixSujetRequest request) {
        return ResponseEntity.ok(sujetService.choisir(request));
    }
}