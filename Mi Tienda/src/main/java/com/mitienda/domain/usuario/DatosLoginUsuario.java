package com.mitienda.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosLoginUsuario(
        @NotBlank
        String usuario,
        @NotBlank
        String clave
) {
}
