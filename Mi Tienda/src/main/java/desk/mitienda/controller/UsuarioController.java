package desk.mitienda.controller;

import desk.mitienda.dao.UsuarioDao;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Usuario> listar(String nombre) {
        return usuarioDao.listar(nombre);
    }

    public Usuario getUsuarioId(Long id) {
        return usuarioDao.getUsuarioId(id);
    }
}
