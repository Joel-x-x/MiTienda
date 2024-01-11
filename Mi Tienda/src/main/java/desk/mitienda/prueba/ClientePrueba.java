package desk.mitienda.prueba;

import desk.mitienda.controller.ClienteController;
import desk.mitienda.model.Cliente;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;

public class ClientePrueba {

    public static void main(String[] args) {
        FlyWay.migrate();

        ClienteController clienteController = new ClienteController();

//        Estado estado = clienteController.guardar(new Cliente(null, "1839433343","Juan","Perez","0938384455"));
//        System.out.println(estado.getMensaje());

        // Actualizar
        Estado estado = clienteController.actualizar(new Cliente(1l, "1839433343","Maria","Perez","0938384455"));
        System.out.println(estado.getMensaje());
    }
}
