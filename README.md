# Frontend – Gestion des Sujets (UASZ)

## Description

Ce frontend est l’interface utilisateur du microservice de gestion des sujets académiques de l’Université Assane Seck de Ziguinchor (UASZ).

Il permet aux différents acteurs (administrateur, enseignant, étudiant) d’interagir avec le système à travers une interface moderne et intuitive.

---

## Fonctionnalités

### Administrateur
- consulter tous les sujets ;
- valider un sujet ;
- rejeter un sujet.

### Enseignant
- proposer un sujet ;
- consulter ses sujets.

### Étudiant
- consulter les sujets validés ;
- choisir un sujet.

---

## Technologies utilisées

- React (Vite)
- Axios
- React Router DOM
- Lucide React
- CSS personnalisé (design UASZ)

---

## Architecture

Le frontend communique avec :

- Microservice utilisateurs : http://localhost:8081/api
- Microservice sujets : http://localhost:8082/api

---

## Installation

### 1. Cloner le projet

```bash
git clone https://github.com/Aissatou183/gestion-sujets-frontend.git
cd gestion-sujets-frontend
