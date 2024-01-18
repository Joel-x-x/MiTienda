package desk.mitienda.dao;

import desk.mitienda.model.Cliente;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.model.Cliente;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

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
            return new Estado(true, "Cliente registrado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar el cliente");
        } finally {
             // em.close();
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
            return new Estado(true, "Cliente actualizado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar el cliente");
        } finally {
             // em.close();
        }
    }
    public Estado eliminar(Long id) {
        Cliente cliente;

        try {
            transaction.begin();
            // Buscamos cliente por id
            cliente = this.em.find(Cliente.class, id);
            // Pasamos de detached a managed
            cliente = this.em.merge(cliente);
            // Desactivamos el estado a false
            cliente.desactivar();
            transaction.commit();
            return new Estado(true, "Cliente eliminado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo eliminar el cliente");
        } finally {
             // em.close();
        }
    }

    public List<Cliente> listar(String identificacion, String nombre) {
        CriteriaBuilder criteriaBuilder =em.getCriteriaBuilder();

        CriteriaQuery<Cliente> createQuery = criteriaBuilder.createQuery(Cliente.class);

        Root<Cliente> from = createQuery.from(Cliente.class);

        Predicate filtro = criteriaBuilder.and();

        if(identificacion != null && !identificacion.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("identificacion"), identificacion));
        }

        if(nombre != null && !nombre.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("nombre"), nombre));
        }

        filtro = criteriaBuilder.and(filtro, criteriaBuilder.isTrue(from.get("estado")));

        return em.createQuery(createQuery.where(filtro)).getResultList();

    }

    public Cliente getClienteId(Long id) {
        return this.em.find(Cliente.class, id);
    }

}
