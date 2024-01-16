package desk.mitienda.controller;

import desk.mitienda.dao.ProveedorDao;
import desk.mitienda.model.Cliente;
import desk.mitienda.model.Proveedor;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class ProveedorController {
    ProveedorDao proveedorDao;

    public ProveedorController() {
        this.proveedorDao = new ProveedorDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(Proveedor proveedor) {
        return proveedorDao.guardar(proveedor);
    }

    public Estado actualizar(Proveedor proveedor) {
        return proveedorDao.actualizar(proveedor);
    }

    public Estado eliminar(Long id) {
        return proveedorDao.eliminar(id);
    }

    public List<Proveedor> listar(String identificacion, String empresa) {
        return proveedorDao.listar(identificacion, empresa);
    }

    public Proveedor getProveedorId(Long id) {
        return proveedorDao.getProveedorId(id);
    }
}
