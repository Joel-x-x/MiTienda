package desk.mitienda.dao;

import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.utils.Estado;
import org.apache.commons.beanutils.BeanUtils;

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
            return new Estado(true, "Datos empresa registrados");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo registrar los datos empresa");
        } finally {
            em.close();
        }
    }

    public Estado actualizar(DatosEmpresa datosEmpresa) {

        DatosEmpresa datosEmpresaExistente;

        try {
            transaction.begin();
            // Buscamos cliente por id
            datosEmpresaExistente = this.em.find(DatosEmpresa.class, datosEmpresa.getId());
            // Pasamos de detached a managed
            datosEmpresaExistente = this.em.merge(datosEmpresaExistente);
            // Copiamos las propiedades del cliente al datosEmpresaExistente
            BeanUtils.copyProperties(datosEmpresaExistente, datosEmpresa);
            transaction.commit();
            return new Estado(true, "Datos empresa actualizados");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revierte la transacción si se produce una excepción
            }
            e.printStackTrace();
            return new Estado(false, "No se pudo actualizar datos empresa");
        } finally {
            em.close();
        }
    }

}
