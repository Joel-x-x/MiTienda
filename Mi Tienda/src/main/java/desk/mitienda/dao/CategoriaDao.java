package desk.mitienda.dao;

import desk.mitienda.model.Categoria;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CategoriaDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public CategoriaDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Categoria categoria) {
        try {
            transaction.begin();
            this.em.persist(categoria);
            transaction.commit();
            return new Estado(true, "Categoria registrada");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar la categoria");
        } finally {
            em.close();
        }
    }

    public Estado actualizar(Categoria categoria) {

        Categoria categoriaExistente;

        try {
            transaction.begin();
            // Buscamos categoria por id
            categoriaExistente = this.em.find(Categoria.class, categoria.getId());
            // Pasamos de detached a managed
            categoriaExistente = this.em.merge(categoriaExistente);
            // Copiamos las propiedades del la categoria a categoriaExistente
            BeanUtils.copyProperties(categoriaExistente, categoria);
            transaction.commit();
            return new Estado(true, "Categoria actualizada");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar la categoria");
        } finally {
            em.close();
        }
    }

}
