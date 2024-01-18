package desk.mitienda.prueba;

import desk.mitienda.controller.ClienteController;
import desk.mitienda.controller.NotaVentaController;
import desk.mitienda.controller.ProductoController;
import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.DetalleNota;
import desk.mitienda.model.NotaVenta;
import desk.mitienda.utils.FlyWay;

import java.time.LocalDate;
import java.util.ArrayList;

public class NotaVentaPrueba {
    public static void main(String[] args) {
        ProductoController productoController = new ProductoController();
        ClienteController clienteController = new ClienteController();
        NotaVentaController notaVentaController = new NotaVentaController();
        UsuarioController usuarioController = new UsuarioController();

        // Nueva venta
        NotaVenta notaVenta = new NotaVenta(notaVentaController.getSiguienteNumeroConsumidorFinal());

        // Agregar productos
        DetalleNota detalleNota = new DetalleNota(productoController.getProductoId(1L));

        // Modificaciones
        detalleNota.setCantidad(3.0);
        detalleNota.recalcular();

        // Agregar detalle a la nota
        notaVenta.agregarDetalle(detalleNota);

        // Agregar datos Nota venta
//        notaVenta.setCliente(clienteController.getClienteId(1L));
        notaVenta.consumidorFinal();
        notaVenta.setUsuario(usuarioController.getUsuarioId(1L));

        notaVentaController.guardar(notaVenta);
    }
}
