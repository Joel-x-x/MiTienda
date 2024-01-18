package desk.mitienda.prueba;

import desk.mitienda.controller.ProductoController;
import desk.mitienda.model.Producto;
import desk.mitienda.utils.FlyWay;

public class KardexPrueba {
    public static void main(String[] args) {
        FlyWay.migrate();
        ProductoController productoController = new ProductoController();

        Producto producto = productoController.getProductoId(1L);

        System.out.println(producto.getPrecioVenta());
    }
}
