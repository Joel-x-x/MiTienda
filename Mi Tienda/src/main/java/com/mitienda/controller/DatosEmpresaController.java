package com.mitienda.controller;

import com.mitienda.domain.datosempresa.DatosEmpresa;
import com.mitienda.domain.datosempresa.DatosEmpresaRepository;
import com.mitienda.domain.datosempresa.DatosRegistrarDatosEmpresa;
import com.mitienda.infra.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class DatosEmpresaController {

    @Autowired
    DatosEmpresaRepository datosEmpresaRepository;


    public Estado registrarDatosEmpresa(DatosRegistrarDatosEmpresa datosRegistrarDatosEmpresa) {
        datosEmpresaRepository.save(new DatosEmpresa(datosRegistrarDatosEmpresa));

        return new Estado(true, "datos almacenados!");
    }
}
