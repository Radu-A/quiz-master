package com.github.Radu_A.evaluacion_final.dto;

public record JwtAuthResponseDto(
        String token,
        String type) {

    public JwtAuthResponseDto(String token) {
        this(token, "Bearer");
    }
}
