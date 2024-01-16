package desk.mitienda.dao;

import desk.mitienda.model.Compra;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompraDao {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public CompraDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Compra compra) {
        try {
            transaction.begin();
            this.em.persist(compra);
            transaction.commit();
            return new Estado(true, "Compra registrada");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar la compra");
        } finally {
            // em.close();
        }
    }

    public List<Compra> listar(String numero, String razonSocial) {
        CriteriaBuilder criteriaBuilder =em.getCriteriaBuilder();

        CriteriaQuery<Compra> createQuery = criteriaBuilder.createQuery(Compra.class);

        Root<Compra> from = createQuery.from(Compra.class);

        Predicate filtro = criteriaBuilder.and();

        if(numero != null && !numero.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("numero"), numero));
        }

        if(razonSocial != null && !razonSocial.trim().isEmpty()) {
            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("razonSocial"), razonSocial));
        }

        filtro = criteriaBuilder.and(filtro, criteriaBuilder.isTrue(from.get("estado")));

        return em.createQuery(createQuery.where(filtro)).getResultList();

    }

    public Compra getCompraId(Long id) {
        return this.em.find(Compra.class, id);
    }

    // Metodo que devuelva el ultimo numero de compra cuando es un proveedor final
    public String getNumeroUltimoProveedorFinal() {
        try {
            String jpql = "select C.numero from Compra as C where C.id = (select max(C.id) from Compra as C) and C.tieneProveedor = false";
            return this.em.createQuery(jpql, String.class).getSingleResult();
        }
         catch (Exception e) {
            e.printStackTrace();
            return "000000000";
         }
    }
}
