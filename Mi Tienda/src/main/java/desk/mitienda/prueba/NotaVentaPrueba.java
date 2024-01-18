package desk.mitienda.prueba;

import desk.mitienda.controller.*;
import desk.mitienda.model.DetalleNota;
import desk.mitienda.model.NotaVenta;

public class NotaVentaPrueba {
    public static void main(String[] args) {
        ProductoController productoController = new ProductoController();
        ClienteController clienteController = new ClienteController();
        NotaVentaController notaVentaController = new NotaVentaController();
        UsuarioController usuarioController = new UsuarioController();
        KardexController kardexController = new KardexController();

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
        notaVenta.setCliente(clienteController.getClienteId(1L));
//        notaVenta.consumidorFinal();
        notaVenta.setUsuario(usuarioController.getUsuarioId(1L));

        notaVentaController.guardar(notaVenta);

        kardexController.registrarKardexVenta(notaVenta);
    }
}
