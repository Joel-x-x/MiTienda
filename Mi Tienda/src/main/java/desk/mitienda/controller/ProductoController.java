package desk.mitienda.controller;

import desk.mitienda.dao.ProductoDao;
import desk.mitienda.model.Producto;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import javax.swing.*;
import java.util.List;

public class ProductoController {
    ProductoDao productoDao;

    public ProductoController() {
        this.productoDao = new ProductoDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(Producto producto) {
        return productoDao.guardar(producto);
    }

    public Estado actualizar(Producto producto) {
        return productoDao.actualizar(producto);
    }

    public Estado eliminar(Long id) {
        return productoDao.eliminar(id);
    }

    public List<Producto> listar(String codigo, String nombre) {
        return productoDao.listar(codigo, nombre);
    }

    public Producto getProductoId(Long id) {
        return productoDao.getProductoId(id);
    }
}
