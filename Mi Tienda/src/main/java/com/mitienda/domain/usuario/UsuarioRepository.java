package com.mitienda.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsuarioAndClave(String usuario, String clave);

    boolean existsByUsuario(String usuario);
}
