package desk.mitienda.prueba;

import desk.mitienda.controller.ClienteController;
import desk.mitienda.model.Cliente;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;

import java.util.List;

public class ClientePrueba {

    public static void main(String[] args) {
        FlyWay.migrate();

        ClienteController clienteController = new ClienteController();

//        Estado estado2 = clienteController.guardar(new Cliente(null, "1839433343","Pablo","Perez","0938384455", true));
//        System.out.println(estado2.getMensaje());

//        List<Cliente> clientes = clienteController.listar(null, "");
//
//        for(Cliente cliente : clientes) {
//            System.out.println(cliente.getNombre());
//        }

        // Actualizar
        Estado estado = clienteController.actualizar(new Cliente(1l, "1839433343","Maria","Perez","0938384455", true));
        System.out.println(estado.getMensaje());

        // Eliminar
//        Estado estado = clienteController.eliminar(1l);
//        System.out.println(estado.getMensaje());
    }
}
