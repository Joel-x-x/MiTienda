package desk.mitienda.prueba;

import desk.mitienda.controller.UsuarioController;
import desk.mitienda.dao.UsuarioDao;
import desk.mitienda.model.Rol;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.JPAUtils;

import javax.persistence.EntityManager;

public class UsuarioPrueba {

    public static void main(String[] args) {

        EntityManager em = JPAUtils.getEntityManager();

        UsuarioController usuarioController = new UsuarioController();

//        Usuario usuario = new Usuario(null, "UsuarioPrueba", "Usuario@1234", "Usuario", "Prueba", Rol.ADMINISTRADOR, true);


//        usuarioDao.registrar(usuario);

        System.out.println(usuarioController.login("UsuarioPrueba","Usuario@1234").getMensaje());

    }
}
