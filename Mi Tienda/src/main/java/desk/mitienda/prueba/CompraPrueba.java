package desk.mitienda.prueba;

import desk.mitienda.controller.CompraController;
import desk.mitienda.controller.KardexController;
import desk.mitienda.controller.ProductoController;
import desk.mitienda.controller.ProveedorController;
import desk.mitienda.model.Compra;
import desk.mitienda.model.DetalleCompra;
import desk.mitienda.service.KardexService;
import desk.mitienda.utils.FlyWay;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CompraPrueba {
    public static void main(String[] args) {
        FlyWay.migrate();

        ProveedorController proveedorController = new ProveedorController();
        ProductoController productoController = new ProductoController();
        CompraController compraController = new CompraController();
        KardexController kardexController = new KardexController();

        // Nueva Compra
        Compra compra = Compra.builder()
                .puntoEmision("001")
                .establecimiento("001")
                .build();
        // Agregar Productos
        DetalleCompra detalleCompra = new DetalleCompra(productoController.getProductoId(1L));

//        DetalleCompra detalleCompra2 = new DetalleCompra(productoController.getProductoId(2L));
        // Actualizar
        detalleCompra.setCantidad(5.0);
        detalleCompra.setPrecioUnitario(new BigDecimal(1.5));
//        detalleCompra.setPrecioUnitario(7.33);
        // Recalcular valores en base a las nuevas modificaciones
        detalleCompra.recalcular();
//        detalleCompra2.recalcular();
        // Agregar a la compra
        compra.agregarDetalle(detalleCompra);
//        compra.agregarDetalle(detalleCompra2);

        // Agregar datos compra
//        compra.setNumero("00000012323");
        compra.setFormaPago("Efectivo");
        compra.setFecha(LocalDate.now());
//        compra.setProveedor(proveedorController.getProveedorId(1L));
        // Proveedor final
        compra.proveedorFinal(compraController.getSiguienteNumeroProveedorFinal());
        // Cierra el frame no pasa nada la no se ha hecho la persistencia
        // Guardar
        compraController.guardar(compra);
        // Kardex
        kardexController.registrarKardexCompra(compra);
//        kardexService.registrarCompra(compra);
    }
}
