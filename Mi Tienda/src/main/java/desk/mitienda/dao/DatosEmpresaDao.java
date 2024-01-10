package desk.mitienda.dao;

import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.utils.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DatosEmpresaDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public DatosEmpresaDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public Estado guardar(DatosEmpresa datosEmpresa) {
        try {
            transaction.begin();
            this.em.persist(datosEmpresa);
            transaction.commit();
            return new Estado(true, "datos empresa registrados");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "no se pudo registrar los datos empresa");
        } finally {
            em.close();
        }
    }

}
