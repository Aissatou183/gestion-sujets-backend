package com.uasz.gestionsujets.repository;

import com.uasz.gestionsujets.entity.ChoixSujet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoixSujetRepository extends JpaRepository<ChoixSujet, Long> {

    boolean existsBySujetId(Long sujetId);

    boolean existsByEtudiantId(Long etudiantId);
}