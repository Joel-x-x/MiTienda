package desk.mitienda.controller;

import desk.mitienda.dao.DatosEmpresaDao;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

public class DatosEmpresaController {
    private DatosEmpresaDao datosEmpresaDao;

    public DatosEmpresaController() {
        this.datosEmpresaDao = new DatosEmpresaDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(DatosEmpresa datosEmpresa) {
        return datosEmpresaDao.guardar(datosEmpresa);
    }

    public Estado actualizar(DatosEmpresa datosEmpresa) {
        return datosEmpresaDao.actualizar(datosEmpresa);
    }
}
