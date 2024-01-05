package com.mitienda.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank
        String usuario,
        @NotBlank
        String clave,
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotNull
        Rol rol
) {
}
