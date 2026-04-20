# Backend – Gestion des Sujets (UASZ)

## Description

Ce microservice backend fait partie du système de gestion des projets académiques de l’Université Assane Seck de Ziguinchor (UASZ).

Il permet de gérer le cycle de vie des sujets académiques, depuis leur proposition par les enseignants jusqu’à leur validation et leur choix par les étudiants.

---

## Objectifs

Ce backend permet de :

- proposer un sujet par un enseignant ;
- consulter les sujets ;
- valider un sujet ;
- rejeter un sujet ;
- consulter les sujets validés ;
- permettre à un étudiant de choisir un sujet ;
- garantir les règles métier (un seul sujet par étudiant, un seul choix par sujet).

---

## Technologies utilisées

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- MySQL
- Maven

---

## Architecture

Ce microservice s’intègre dans une architecture microservices :

- Microservice utilisateurs : http://localhost:8081
- Microservice sujets : http://localhost:8082

---

## Structure du projet

```bash
src/main/java/com/uasz/gestionsujets
│
├── config
│   ├── CorsConfig.java
│   └── SecurityConfig.java
│
├── controller
│   └── SujetController.java
│
├── dto
│   ├── SujetRequest.java
│   └── ChoixSujetRequest.java
│
├── entity
│   ├── Sujet.java
│   ├── ChoixSujet.java
│   └── StatutSujet.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── repository
│   ├── SujetRepository.java
│   └── ChoixSujetRepository.java
│
├── security
│   ├── JwtAuthFilter.java
│   └── JwtService.java
│
├── service
│   └── SujetService.java
│
└── GestionSujetsBackendApplication.java# Backend – Gestion des Sujets (UASZ)

## Description

Ce microservice backend fait partie du système de gestion des projets académiques de l’Université Assane Seck de Ziguinchor (UASZ).

Il permet de gérer le cycle de vie des sujets académiques, depuis leur proposition par les enseignants jusqu’à leur validation et leur choix par les étudiants.

---

## Objectifs

Ce backend permet de :

- proposer un sujet par un enseignant ;
- consulter les sujets ;
- valider un sujet ;
- rejeter un sujet ;
- consulter les sujets validés ;
- permettre à un étudiant de choisir un sujet ;
- garantir les règles métier (un seul sujet par étudiant, un seul choix par sujet).

---

## Technologies utilisées

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- MySQL
- Maven

---

## Architecture

Ce microservice s’intègre dans une architecture microservices :

- Microservice utilisateurs : http://localhost:8081
- Microservice sujets : http://localhost:8082

---

## Structure du projet

```bash
src/main/java/com/uasz/gestionsujets
│
├── config
│   ├── CorsConfig.java
│   └── SecurityConfig.java
│
├── controller
│   └── SujetController.java
│
├── dto
│   ├── SujetRequest.java
│   └── ChoixSujetRequest.java
│
├── entity
│   ├── Sujet.java
│   ├── ChoixSujet.java
│   └── StatutSujet.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── repository
│   ├── SujetRepository.java
│   └── ChoixSujetRepository.java
│
├── security
│   ├── JwtAuthFilter.java
│   └── JwtService.java
│
├── service
│   └── SujetService.java
│
└── GestionSujetsBackendApplication.java# Frontend – Gestion des Sujets (UASZ)

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
