package desk.mitienda.controller;

import desk.mitienda.dao.CategoriaDao;
import desk.mitienda.model.Categoria;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class CategoriaController {
    CategoriaDao categoriaDao;

    public CategoriaController() {
        this.categoriaDao = new CategoriaDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(Categoria categoria) {
        return categoriaDao.guardar(categoria);
    }

    public Estado actualizar(Categoria categoria) {
        return categoriaDao.actualizar(categoria);
    }

    public List<Categoria> listar() {
        return categoriaDao.listar();
    }

    public Categoria getCategoriaId(Long id) {
        return categoriaDao.getCategoriaId(id);
    }
}
