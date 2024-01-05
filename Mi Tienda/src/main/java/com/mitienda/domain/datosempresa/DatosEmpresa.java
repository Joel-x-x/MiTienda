package com.mitienda.domain.datosempresa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "datos_empresa")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class DatosEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private String celular;
    private String correo;
    private String identificacion;
    private Boolean estado;

    public DatosEmpresa(DatosRegistrarDatosEmpresa datosRegistrarDatosEmpresa) {
        this.nombre = datosRegistrarDatosEmpresa.nombre();
        this.direccion = datosRegistrarDatosEmpresa.direccion();
        this.celular = datosRegistrarDatosEmpresa.celular();
        this.correo = datosRegistrarDatosEmpresa.correo();
        this.identificacion = datosRegistrarDatosEmpresa.identificacion();
        this.estado = true;
    }
}
