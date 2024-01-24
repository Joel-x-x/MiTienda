package desk.mitienda.dao;

import desk.mitienda.model.Kardex;
import desk.mitienda.model.Kardex;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<Kardex> listar(Long id) {
        try {
            String jpql = "select K from Kardex as K where K.producto.id = :id";
            return this.em.createQuery(jpql, Kardex.class).setParameter("id", id).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Kardex> listarFiltroFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Kardex> createQuery = criteriaBuilder.createQuery(Kardex.class);

        Root<Kardex> from = createQuery.from(Kardex.class);

        Predicate filtro = criteriaBuilder.and(criteriaBuilder.between(from.get("fecha"), fechaInicio, fechaFin));

        return em.createQuery(createQuery.where(filtro)).getResultList();
    }

}
