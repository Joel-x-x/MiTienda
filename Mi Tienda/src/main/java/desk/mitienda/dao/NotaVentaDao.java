package desk.mitienda.dao;

import desk.mitienda.model.Cliente;
import desk.mitienda.model.NotaVenta ;
import desk.mitienda.utils.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.*;
import java.util.List;

public class NotaVentaDao {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public NotaVentaDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(NotaVenta notaVenta) {
        try {
            transaction.begin();
            this.em.persist(notaVenta);
            transaction.commit();
            return new Estado(true, "NotaVenta registrada");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar la notaVenta");
        } finally {
            // em.close();
        }
    }

    public List<NotaVenta > listar(String numero, String identificacion) {
        CriteriaBuilder criteriaBuilder =em.getCriteriaBuilder();

        CriteriaQuery<NotaVenta > createQuery = criteriaBuilder.createQuery(NotaVenta .class);

        Root<NotaVenta > from = createQuery.from(NotaVenta.class);

        Join<NotaVenta, Cliente> clienteJoin = from.join("cliente");

        Predicate filtro = criteriaBuilder.and();

        if(numero != null && !numero.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("numero"), numero));
        }

        if(identificacion != null && !identificacion.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(clienteJoin.get("identificacion"), identificacion));
        }

        return em.createQuery(createQuery.where(filtro)).getResultList();

    }

    public NotaVenta getNotaVentaId(Long id) {
        return this.em.find(NotaVenta .class, id);
    }

    // Metodo que devuelva el ultimo numero de notaVenta cuando es un proveedor final
    public String getNumeroUltimoConsumidorFinal() {
        try {
            String jpql = "select N.numero from NotaVenta as N where N.id = (select max(N.id) from NotaVenta as N)";
            return this.em.createQuery(jpql, String.class).getSingleResult();
        }
         catch (Exception e) {
            e.printStackTrace();
            return "000000000";
         }
    }
}
