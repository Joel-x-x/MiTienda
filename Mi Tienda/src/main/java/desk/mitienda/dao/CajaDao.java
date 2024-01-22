package desk.mitienda.dao;

import desk.mitienda.model.Caja;
import desk.mitienda.model.Cliente;
import desk.mitienda.model.Caja;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.*;
import java.util.List;

public class CajaDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public CajaDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Caja caja) {
        try {
            transaction.begin();
            this.em.persist(caja);
            transaction.commit();
            return new Estado(true, "Apertura exitosa");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo abrir caja");
        } finally {
            // em.close();
        }
    }

    public Estado actualizar(Caja caja) {

        Caja cajaExistente;

        try {
            transaction.begin();
            // Buscamos caja por id
            cajaExistente = this.em.find(Caja.class, caja.getId());
            // Pasamos de detached a managed
            cajaExistente = this.em.merge(cajaExistente);
            // Copiamos las propiedades del la caja a cajaExistente
            BeanUtils.copyProperties(cajaExistente, caja);
            transaction.commit();
            return new Estado(true, "Caja actualizada");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar la caja");
        } finally {
            // em.close();
        }
    }

    public List<Caja> listar(String nombre) {
        CriteriaBuilder criteriaBuilder =em.getCriteriaBuilder();

        CriteriaQuery<Caja > createQuery = criteriaBuilder.createQuery(Caja .class);

        Root<Caja > from = createQuery.from(Caja.class);

        Join<Caja, Usuario> usuarioJoin = from.join("usuario");

        Predicate filtro = criteriaBuilder.and();

        if(nombre != null && !nombre.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(usuarioJoin.get("nombre"), nombre));
        }

        return em.createQuery(createQuery.where(filtro)).getResultList();
    }

    public Caja getCajaAbiertaUsuarioId(Long id) {
        try {
            String jpql = "select C from Caja as C where C.cerrada = false and C.usuario.id = :id";
            return this.em.createQuery(jpql, Caja.class).setParameter("id", id).getSingleResult();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Caja getCajaId(Long id) {
        return this.em.find(Caja.class, id);
    }

}
