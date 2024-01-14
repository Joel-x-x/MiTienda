package desk.mitienda.prueba;

import desk.mitienda.controller.CategoriaController;
import desk.mitienda.controller.IvaController;
import desk.mitienda.controller.ProductoController;
import desk.mitienda.model.Categoria;
import desk.mitienda.model.Iva;
import desk.mitienda.model.Producto;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;

import java.util.List;

public class ProductoPrueba {
    public static void main(String[] args) {
        FlyWay.migrate();

        ProductoController productoController = new ProductoController();

        CategoriaController categoriaController = new CategoriaController();

        IvaController ivaController = new IvaController();

//        ivaController.guardar(Iva.builder().iva(12.0).build());

//        categoriaController.guardar(Categoria.builder().categoria("General").build());

        // Guardar producto
//        Estado estado = productoController.guardar(Producto
//                .builder()
//                .codigo("DDKF4")
//                .nombre("Piñata miñion")
//                .descripcion("Piñata de minion")
//                .categoria(categoriaController.getCategoriaId(1L))
//                .iva(ivaController.ultimoIva())
//                .tieneIva(true)
//                .utilidad(30.0)
//                .estado(true)
//                .stock(0.0)
//                .build()
//        );
//
//        System.out.println(estado.getMensaje());

        // Eliminar
//        productoController.eliminar(2L);

        List<Producto> productos = productoController.listar(null, "");

        for(Producto producto : productos) {
            System.out.println(producto.getNombre());
            System.out.println(producto.getCategoria().getCategoria());
            System.out.println(producto.getStock());
        }
    }
}
