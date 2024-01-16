package desk.mitienda.prueba;

import desk.mitienda.controller.CompraController;
import desk.mitienda.controller.ProductoController;
import desk.mitienda.controller.ProveedorController;
import desk.mitienda.model.Compra;
import desk.mitienda.model.DetalleCompra;
import desk.mitienda.utils.FlyWay;

import java.time.LocalDate;

public class CompraPrueba {
    public static void main(String[] args) {
        FlyWay.migrate();

        ProveedorController proveedorController = new ProveedorController();
        ProductoController productoController = new ProductoController();
        CompraController compraController = new CompraController();

        // Nueva Compra
        Compra compra = new Compra();
        // Agregar Productos
        DetalleCompra detalleCompra = new DetalleCompra(productoController.getProductoId(1L));
        detalleCompra.inicializar();

        DetalleCompra detalleCompra2 = new DetalleCompra(productoController.getProductoId(2L));
        detalleCompra2.inicializar();
        // Actualizar
//        detalleCompra.setPrecioUnitario(7.33);

        // Agregar a la compra
        compra.agregarDetalle(detalleCompra);
        compra.agregarDetalle(detalleCompra2);

        // Agregar datos compra
        compra.setPuntoEmision("001");
        compra.setEstablecimiento("001");
        compra.setNumero("00000012323");
        compra.setFormaPago("Efectivo");
        compra.setProveedor(proveedorController.getProveedorId(1L));
        compra.setTieneProveedor(true);
        // Cierra el frame no pasa nada la no se ha hecho la persistencia
        // Guardar
        compraController.guardar(compra);
//        Compra compra = Compra
//                .builder()
//                .puntoEmision("001")
//                .establecimiento("001")
//                .numero("00000012323")
//                .fecha(LocalDate.now())
//                .formaPago("Efecto")
//                .proveedor(proveedorController.getProveedorId(1L))
//                .tieneProveedor(true)
//                .build();
//
//        DetalleCompra detalleCompra = DetalleCompra
//                .builder()
//                .producto(productoController.getProductoId(1L))
//                .cantidad(0.0)
//                .precioUnitario(0.0)
//                .subtotal(0.0)
//                .iva(0.0)
//                .total(0.0)
//                .compra(compra)
//                .build();
    }
}
