package desk.mitienda.prueba;

import desk.mitienda.controller.DatosEmpresaController;
import desk.mitienda.model.DatosEmpresa;
import org.flywaydb.core.Flyway;

public class DatosEmpresaPrueba {

    public static void main(String[] args) {
        DatosEmpresaController datosEmpresaController = new DatosEmpresaController();

        datosEmpresaController.guardar(new DatosEmpresa(null, "Empresa", "Direccion", "09454449","correo@correo.com",true, "1850034232"));
    }
}
