package desk.mitienda.controller;

import desk.mitienda.dao.IvaDao;
import desk.mitienda.model.Iva;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class IvaController {

    private IvaDao ivaDao;

    public IvaController() {
        ivaDao = new IvaDao(JPAUtils.getEntityManager());
    }

    public Estado actualizar(Iva iva) {
        return ivaDao.actualizar(iva);
    }

    public List<Iva> listar() {
        return ivaDao.listar();
    }
}
