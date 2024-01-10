package desk.mitienda.controller;

import desk.mitienda.dao.UsuarioDao;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import javax.persistence.EntityManager;

public class UsuarioController {

    UsuarioDao usuarioDao;

    public UsuarioController() {
        this.usuarioDao = new UsuarioDao(JPAUtils.getEntityManager());
    }

    public Estado registrar(Usuario usuario) {
        return usuarioDao.registrar(usuario);
    }

    public Estado login(String usuario, String clave) {
        return usuarioDao.login(usuario, clave);
    }
}
