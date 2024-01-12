package desk.mitienda.dao;

import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UsuarioDao {

    private EntityManager em;
    private EntityTransaction transaction;

    public UsuarioDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado registrar(Usuario usuario) {
        try {
            transaction.begin();
            this.em.persist(usuario);
            transaction.commit();
            return new Estado(true, "Usuario registrado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar el usuario");
        } finally {
            em.close();
        }
    }

    public Estado login(String usuario, String clave) {
        String jpql = "select U from Usuario as U where U.usuario = :usuario and U.clave = :clave";

        try {
            transaction.begin();
            Usuario usuarioClass = this.em.createQuery(jpql, Usuario.class).setParameter("usuario", usuario).setParameter("clave", clave).getSingleResult();
            transaction.commit();
            return new Estado(true, "Usuario autenticado");
        } catch (Exception e) {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new Estado(false, "Credenciales incorrectas");
        } finally {
            em.close();
        }
    }

}
