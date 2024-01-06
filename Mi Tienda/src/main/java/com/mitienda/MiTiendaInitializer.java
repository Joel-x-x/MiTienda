
package com.mitienda;

import com.mitienda.controller.DatosEmpresaController;
import com.mitienda.controller.UsuarioController;
import com.mitienda.domain.datosempresa.DatosRegistrarDatosEmpresa;
import com.mitienda.domain.usuario.DatosRegistroUsuario;
import com.mitienda.domain.usuario.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MiTiendaInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private DatosEmpresaController datosEmpresaController;

    @Override
    public void run(String... args) {
        // Operaciones de inicialización, por ejemplo:
        // usuarioController.registrarUsuario(new DatosRegistroUsuario("Prueba","1234","Prueba","Prueba", Rol.ADMINISTRADOR));
        // datosEmpresaController.registrarDatosEmpresa(new DatosRegistrarDatosEmpresa("Fiestas Coyote", "Av. 8", "094848584","coyote@gmail.com","1850034232001"));
    }
}
