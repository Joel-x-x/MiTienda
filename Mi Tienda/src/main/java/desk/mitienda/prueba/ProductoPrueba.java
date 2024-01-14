package desk.mitienda.prueba;

import desk.mitienda.controller.CategoriaController;
import desk.mitienda.controller.IvaController;
import desk.mitienda.controller.ProductoController;
import desk.mitienda.model.Categoria;
import desk.mitienda.model.Iva;
import desk.mitienda.utils.FlyWay;

public class ProductoPrueba {
    public static void main(String[] args) {
        FlyWay.migrate();

        ProductoController productoController = new ProductoController();

        CategoriaController categoriaController = new CategoriaController();

        IvaController ivaController = new IvaController();

        ivaController.actualizar(Iva.builder().iva(12.0).build());

        categoriaController.guardar(Categoria.builder().categoria("General").build());
    }
}
