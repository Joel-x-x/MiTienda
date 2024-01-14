package desk.mitienda.prueba;

import desk.mitienda.controller.ClienteController;
import desk.mitienda.controller.ProveedorController;
import desk.mitienda.model.Cliente;
import desk.mitienda.model.Proveedor;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;

public class ProveedorPrueba {

    public static void main(String[] args) {
        FlyWay.migrate();

        ProveedorController proveedorController = new ProveedorController();

//        Estado estado = proveedorController.guardar(new Proveedor(null, "1839433343","Fausto Perez","Pinguino","Manabi y Guayaquil frente a la farmacia el descuento",
//                "0938384455","correo@corre.com","Vende helados", true));
//        System.out.println(estado.getMensaje());

        // Actualizar
//        Estado estado = proveedorController.actualizar(
//                new Proveedor(1L, "1839433343","Fausto Perez","Pinguino","Manabi y Guayaquil frente a la farmacia el descuento",
//                "0938384455","correo@corre.com","Vende helados pinguino", true));
//        System.out.println(estado.getMensaje());



    }
}
