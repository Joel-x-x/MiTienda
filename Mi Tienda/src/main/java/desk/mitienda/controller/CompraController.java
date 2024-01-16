package desk.mitienda.controller;

import desk.mitienda.dao.CompraDao;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Compra;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class CompraController {
    CompraDao compraDao;

    public CompraController() {
        this.compraDao = new CompraDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(Compra compra) {
        return compraDao.guardar(compra);
    }

    public List<Compra> listar(String codigo, String nombre) {
        return compraDao.listar(codigo, nombre);
    }

    public Compra getCompraId(Long id) {
        return compraDao.getCompraId(id);
    }
}
