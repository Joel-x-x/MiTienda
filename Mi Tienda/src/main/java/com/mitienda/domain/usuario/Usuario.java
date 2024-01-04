package com.mitienda.domain.usuario;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String usuario;

    private String clave;
    private String nombre;
    private String apellido;
    private String identificacion;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private Boolean estado;


}
