package desk.mitienda.dao;

import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;

import javax.persistence.EntityManager;

public class UsuarioDao {

    private EntityManager em;

    public UsuarioDao(EntityManager em) {
        this.em = em;
    }

    public Estado registrar(Usuario usuario) {


        return new Estado(true, "usuario registrado");
    }

}
