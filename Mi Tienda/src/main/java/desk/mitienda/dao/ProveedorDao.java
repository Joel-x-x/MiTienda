package desk.mitienda.dao;

import desk.mitienda.model.Cliente;
import desk.mitienda.model.Proveedor;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProveedorDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public ProveedorDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Proveedor proveedor) {
        try {
            transaction.begin();
            this.em.persist(proveedor);
            transaction.commit();
            return new Estado(true, "Proveedor registrado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar el proveedor");
        } finally {
            em.close();
        }
    }

    public Estado actualizar(Proveedor proveedor) {

        Proveedor proveedorExistente;

        try {
            transaction.begin();
            // Buscamos proveedor por id
            proveedorExistente = this.em.find(Proveedor.class, proveedor.getId());
            // Pasamos de detached a managed
            proveedorExistente = this.em.merge(proveedorExistente);
            // Copiamos las propiedades del proveedor al proveedorExistente
            BeanUtils.copyProperties(proveedorExistente, proveedor);
            transaction.commit();
            return new Estado(true, "Proveedor actualizado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar el proveedor");
        } finally {
            em.close();
        }
    }

    public Estado eliminar(Long id) {
        Proveedor proveedor;

        try {
            transaction.begin();
            // Buscamos proveedor por id
            proveedor = this.em.find(Proveedor.class, id);
            // Pasamos de detached a managed
            proveedor = this.em.merge(proveedor);
            // Desactivamos el estado a false
            proveedor.desactivar();
            transaction.commit();
            return new Estado(true, "Proveedor eliminado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo eliminar el proveedor");
        } finally {
            em.close();
        }
    }

}
