package com.uasz.gestionsujets.client;

import com.uasz.gestionsujets.dto.UtilisateurResponse;
import com.uasz.gestionsujets.exception.BadRequestException;
import com.uasz.gestionsujets.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UtilisateurClient {

    private final HttpServletRequest request;
    private final RestTemplate restTemplate;

    @Value("${utilisateur.service.url}")
    private String utilisateurServiceUrl;

    public UtilisateurResponse getUtilisateurById(Long utilisateurId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && !authHeader.isBlank()) {
                headers.set("Authorization", authHeader);
            }

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<UtilisateurResponse> response = restTemplate.exchange(
                    utilisateurServiceUrl + "/utilisateurs/" + utilisateurId,
                    HttpMethod.GET,
                    entity,
                    UtilisateurResponse.class
            );

            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Utilisateur introuvable avec l'id : " + utilisateurId);
        } catch (HttpClientErrorException.Forbidden e) {
            throw new BadRequestException("Accès refusé lors de l'appel au microservice utilisateur");
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new BadRequestException("Authentification requise pour appeler le microservice utilisateur");
        } catch (HttpClientErrorException e) {
            throw new BadRequestException("Erreur microservice utilisateur : " + e.getResponseBodyAsString());
        } catch (Exception e) {
            throw new BadRequestException("Impossible de contacter le microservice utilisateur");
        }
    }
}