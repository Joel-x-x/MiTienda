package desk.mitienda.dao;

import desk.mitienda.model.Categoria;
import desk.mitienda.model.Iva;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class IvaDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public IvaDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Iva iva) {
        try {
            transaction.begin();
            this.em.persist(iva);
            transaction.commit();
            return new Estado(true, "iva registrada");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "no se pudo registrar la iva");
        } finally {
            // em.close();
        }
    }

    public Estado actualizar(Iva iva) {

        Iva ivaExistente;

        try {
            transaction.begin();
            // Buscamos iva por id
            ivaExistente = this.em.find(Iva.class, iva.getId());
            // Pasamos de detached a managed
            ivaExistente = this.em.merge(ivaExistente);
            // Copiamos las propiedades del la iva a ivaExistente
            BeanUtils.copyProperties(ivaExistente, iva);
            transaction.commit();
            return new Estado(true, "Iva actualizado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar la iva");
        } finally {
            // em.close();
        }
    }

    public List<Iva> listar() {
        String jpql = "select I from Iva as I order by id desc";
        return this.em.createQuery(jpql, Iva.class).getResultList();
    }

    public Iva ultimoIva() {
        String jpql = "select I from Iva as I where id = (select max(I.id) from Iva as I)";
        return this.em.createQuery(jpql, Iva.class).getSingleResult();
    }

}
