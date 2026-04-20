package com.uasz.gestionsujets.repository;

import com.uasz.gestionsujets.entity.StatutSujet;
import com.uasz.gestionsujets.entity.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SujetRepository extends JpaRepository<Sujet, Long> {

    List<Sujet> findByStatut(StatutSujet statut);

    List<Sujet> findByEnseignantId(Long enseignantId);
}