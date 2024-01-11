package desk.mitienda.dao;

import desk.mitienda.model.Cliente;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ClienteDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public ClienteDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Cliente cliente) {
        try {
            transaction.begin();
            this.em.persist(cliente);
            transaction.commit();
            return new Estado(true, "cliente registrado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "no se pudo registrar el cliente");
        } finally {
            em.close();
        }
    }

    public Estado actualizar(Cliente cliente) {

        Cliente clienteExistente;

        try {
            transaction.begin();
            // Buscamos cliente por id
            clienteExistente = this.em.find(Cliente.class, cliente.getId());
            // Pasamos de detached a managed
            clienteExistente = this.em.merge(clienteExistente);
            // Copiamos las propiedades del cliente al clienteExistente
            BeanUtils.copyProperties(clienteExistente, cliente);
            transaction.commit();
            return new Estado(true, "cliente actualizado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "no se pudo actualizar el cliente");
        } finally {
            em.close();
        }
    }

}
