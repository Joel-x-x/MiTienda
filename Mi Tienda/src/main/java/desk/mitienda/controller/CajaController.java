package desk.mitienda.controller;

import desk.mitienda.dao.CajaDao;
import desk.mitienda.model.Caja;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class CajaController {
    CajaDao cajaDao;

    public CajaController() {
        this.cajaDao = new CajaDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(Caja caja) {
        return cajaDao.guardar(caja);
    }

    public Estado actualizar(Caja caja) {
        return cajaDao.actualizar(caja);
    }

    public List<Caja> listar(String nombre) {
        return cajaDao.listar(nombre);
    }

    public Caja getCajaAbiertaUsuarioId(Long id) {
        return cajaDao.getCajaAbiertaUsuarioId(id);
    }

    public Caja getCajaId(Long id) {
        return cajaDao.getCajaId(id);
    }
}
