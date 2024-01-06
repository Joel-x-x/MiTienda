package com.mitienda.controller;

import com.mitienda.domain.usuario.*;
import com.mitienda.infra.Estado;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RegistrarUsuarioService registrarUsuarioService;

    public Estado registrarUsuario(@Valid DatosRegistroUsuario datosRegistroUsuario) {
        registrarUsuarioService.registrarUsuario(datosRegistroUsuario);

        return new Estado(true, "usuario registrado!");
    }

    public Estado login(@Valid DatosLoginUsuario datosLoginUsuario) {
       Usuario usuario = usuarioRepository.findByUsuarioAndClave(datosLoginUsuario.usuario(), datosLoginUsuario.clave());

       if(usuario == null)
           return new Estado(false, "datos incorrectos");

       return new Estado(true, "datos correctos");
    }

}
