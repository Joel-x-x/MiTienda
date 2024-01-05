package com.mitienda.controller;


import com.mitienda.domain.usuario.DatosLoginUsuario;
import com.mitienda.domain.usuario.DatosRegistroUsuario;
import com.mitienda.domain.usuario.Usuario;
import com.mitienda.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Boolean registrarUsuarioAdministrador(DatosRegistroUsuario datosRegistroUsuario) {
        usuarioRepository.save(new Usuario(datosRegistroUsuario));

        return true;
    }

    public Boolean login(DatosLoginUsuario datosLoginUsuario) {
       Usuario usuario = usuarioRepository.findByUsuarioAndClave(datosLoginUsuario.usuario(), datosLoginUsuario.clave());

       if(usuario == null)
           return false;

       return true;
    }


}
