package com.mitienda.domain.datosempresa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistrarDatosEmpresa(
     @NotBlank
     String nombre,
     @NotBlank
     String direccion,
     @NotBlank
     String celular,
     @Email
     @NotBlank
     String correo,
     @NotBlank
     String identificacion
) {
}
