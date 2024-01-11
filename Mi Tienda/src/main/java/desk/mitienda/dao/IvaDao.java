package desk.mitienda.dao;

import desk.mitienda.model.Iva;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
            em.close();
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
            return new Estado(true, "iva actualizada");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "no se pudo actualizar la iva");
        } finally {
            em.close();
        }
    }

}
