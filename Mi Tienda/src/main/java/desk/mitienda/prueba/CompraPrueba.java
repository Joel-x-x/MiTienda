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
