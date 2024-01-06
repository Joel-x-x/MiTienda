package com.mitienda.domain.usuario.validaciones;

import com.mitienda.domain.usuario.DatosRegistroUsuario;
import com.mitienda.infra.Estado;

public interface ValidacionesRegistrarUsuario {
    public void validar(DatosRegistroUsuario datosRegistroUsuario);
}
