package com.mitienda.controller;

import com.mitienda.domain.datosempresa.DatosEmpresa;
import com.mitienda.domain.datosempresa.DatosEmpresaRepository;
import com.mitienda.domain.datosempresa.DatosRegistrarDatosEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatosEmpresaController {

    @Autowired
    DatosEmpresaRepository datosEmpresaRepository;


    public Boolean registrarDatosEmpresa(DatosRegistrarDatosEmpresa datosRegistrarDatosEmpresa) {
        datosEmpresaRepository.save(new DatosEmpresa(datosRegistrarDatosEmpresa));

        return true;
    }
}
