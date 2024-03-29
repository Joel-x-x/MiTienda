package desk.mitienda.controller;

import desk.mitienda.dao.ClienteDao;
import desk.mitienda.model.Cliente;
import desk.mitienda.model.Cliente;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

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

    public Estado eliminar(Long id) {
        return clienteDao.eliminar(id);
    }

    public List<Cliente> listar(String identificacion, String nombre) {
        return clienteDao.listar(identificacion, nombre);
    }

    public Cliente getClienteId(Long id) {
        return clienteDao.getClienteId(id);
    }
}
