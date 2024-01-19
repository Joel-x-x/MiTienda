package desk.mitienda.dao;

import desk.mitienda.model.Usuario;
import desk.mitienda.model.Proveedor;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
            // em.close();
        }
    }
    public Estado actualizar(Usuario usuario) {
        Usuario usuarioExistente;
        try{
            transaction.begin();
            // Buscamos proveedor por id
            usuarioExistente = this.em.find(Usuario.class, usuario.getId());
            // Pasamos de detached a managed
            usuarioExistente = this.em.merge(usuarioExistente);
            // Copiamos las propiedades del proveedor al proveedorExistente
            BeanUtils.copyProperties(usuarioExistente, usuario);
            transaction.commit();
            return new Estado(true, "Usuario actualizado");
        }catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar el Usuario");
        } finally {
            // em.close();
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
            // em.close();
        }
    }

    public List<Usuario> listar(String nombre) {
        try {
            String jpql = "select U from Usuario as U where U.nombre like :nombre and U.estado <> true";
            return this.em.createQuery(jpql, Usuario.class).setParameter("nombre", nombre).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Usuario getUsuarioId(Long id) {
        return this.em.find(Usuario.class, id);
    }


}
