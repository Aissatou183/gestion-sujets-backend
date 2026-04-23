package com.uasz.gestionsujets.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticatedUserDto {
    private Long id;
    private String email;
    private String role;
}