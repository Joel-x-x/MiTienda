package com.mitienda.domain.usuario.validaciones;

import com.mitienda.domain.usuario.DatosRegistroUsuario;
import com.mitienda.domain.usuario.UsuarioRepository;
import com.mitienda.infra.Estado;
import com.mitienda.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepetido implements ValidacionesRegistrarUsuario{
    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public void validar(DatosRegistroUsuario datosRegistroUsuario) {
        if(usuarioRepository.existsByUsuario(datosRegistroUsuario.usuario())){
            throw new IntegrityValidation("usuario repetido");
        }
    }
}
