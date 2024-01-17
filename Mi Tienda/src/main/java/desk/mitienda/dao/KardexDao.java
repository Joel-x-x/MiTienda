package desk.mitienda.dao;

import desk.mitienda.model.Kardex;
import desk.mitienda.utils.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class KardexDao {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public KardexDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(Kardex kardex) {
        try {
            transaction.begin();
            this.em.persist(kardex);
            transaction.commit();
            return new Estado(true, "Kardex registrado");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar el kardex");
        } finally {
            // em.close();
        }
    }

//    public List<Kardex> listar(String numero, String razonSocial) {
//        CriteriaBuilder criteriaBuilder =em.getCriteriaBuilder();
//
//        CriteriaQuery<Kardex> createQuery = criteriaBuilder.createQuery(Kardex.class);
//
//        Root<Kardex> from = createQuery.from(Kardex.class);
//
//        Predicate filtro = criteriaBuilder.and();
//
//        if(numero != null && !numero.trim().isEmpty()) {
//            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("numero"), numero));
//        }
//
//        if(razonSocial != null && !razonSocial.trim().isEmpty()) {
//            filtro = criteriaBuilder.and(filtro, criteriaBuilder.like(from.get("razonSocial"), razonSocial));
//        }
//
//        filtro = criteriaBuilder.and(filtro, criteriaBuilder.isTrue(from.get("estado")));
//
//        return em.createQuery(createQuery.where(filtro)).getResultList();
//
//    }

}
