package desk.mitienda.controller;

import desk.mitienda.dao.ClienteDao;
import desk.mitienda.model.Cliente;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

public class ClienteController {
    ClienteDao clienteDao;

    public ClienteController() {
        this.clienteDao = new ClienteDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(Cliente cliente) {
        return clienteDao.guardar(cliente);
    }

    public Estado actualizar(Cliente cliente) {
        return clienteDao.actualizar(cliente);
    }
}
