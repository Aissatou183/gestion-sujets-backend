package com.uasz.gestionsujets.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChoixSujetRequest {

    @NotNull(message = "L'identifiant du sujet est obligatoire")
    private Long sujetId;
}