package com.mitienda.domain.usuario;

import com.mitienda.domain.usuario.validaciones.ValidacionesRegistrarUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrarUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidacionesRegistrarUsuario> validaciones;
    public Usuario registrarUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        validaciones.forEach(x -> x.validar(datosRegistroUsuario));

        return usuarioRepository.save(new Usuario(datosRegistroUsuario));
    }

}
