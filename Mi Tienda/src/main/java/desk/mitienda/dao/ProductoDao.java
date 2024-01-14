package desk.mitienda.dao;

import desk.mitienda.model.Producto;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductoDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public ProductoDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Producto producto) {
        try {
            transaction.begin();
            this.em.persist(producto);
            transaction.commit();
            return new Estado(true, "Producto registrado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar el producto");
        } finally {
            // em.close();
        }
    }

    public Estado actualizar(Producto producto) {

        Producto productoExistente;

        try {
            transaction.begin();
            // Buscamos producto por id
            productoExistente = this.em.find(Producto.class, producto.getId());
            // Pasamos de detached a managed
            productoExistente = this.em.merge(productoExistente);
            // Copiamos las propiedades del producto al productoExistente
            BeanUtils.copyProperties(productoExistente, producto);
            transaction.commit();
            return new Estado(true, "Producto actualizado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar el producto");
        } finally {
            // em.close();
        }
    }

    public Estado eliminar(Long id) {
        Producto producto;

        try {
            transaction.begin();
            // Buscamos producto por id
            producto = this.em.find(Producto.class, id);
            // Pasamos de detached a managed
            producto = this.em.merge(producto);
            // Desactivamos el estado a false
            producto.desactivar();
            transaction.commit();
            return new Estado(true, "Producto eliminado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo eliminar el producto");
        } finally {
            // em.close();
        }
    }

    public List<Producto> listar(String codigo, String nombre) {
        CriteriaBuilder criteriaBuilder =em.getCriteriaBuilder();

        CriteriaQuery<Producto> createQuery = criteriaBuilder.createQuery(Producto.class);

        Root<Producto> from = createQuery.from(Producto.class);

        Predicate filtro = criteriaBuilder.and();

        if(codigo != null && !codigo.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("codigo"), codigo));
        }

        if(nombre != null && !nombre.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("nombre"), nombre));
        }

        filtro = criteriaBuilder.and(filtro, criteriaBuilder.isTrue(from.get("estado")));

        return em.createQuery(createQuery.where(filtro)).getResultList();

    }

}
