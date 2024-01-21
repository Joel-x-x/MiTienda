package desk.mitienda.prueba;

import desk.mitienda.controller.DatosEmpresaController;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;
import org.flywaydb.core.Flyway;

public class DatosEmpresaPrueba {

    public static void main(String[] args) {
        FlyWay.migrate();

        DatosEmpresaController datosEmpresaController = new DatosEmpresaController();

//        Estado estado = datosEmpresaController.guardar(new DatosEmpresa(null, "Empresa", "Paci", "Direccion", "0999999999","correo@correo.com",true, "1850034232"));
//        System.out.println(estado.getMensaje());
//        Estado estado = datosEmpresaController.actualizar(new DatosEmpresa(1L, "Empresa", "Juan Pablo Ruiz Palacios", "Direccion", "0999999999","correo@correo.com",true, "1850034232"));
//        System.out.println(estado.getMensaje());

    }
}
