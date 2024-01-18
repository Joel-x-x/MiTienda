package desk.mitienda.controller;

import desk.mitienda.dao.NotaVentaDao;
import desk.mitienda.model.NotaVenta;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class NotaVentaController {
    NotaVentaDao notaVentaDao;

    public NotaVentaController() {
        this.notaVentaDao = new NotaVentaDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(NotaVenta notaVenta) {
        return notaVentaDao.guardar(notaVenta);
    }

    public List<NotaVenta> listar(String codigo, String nombre) {
        return notaVentaDao.listar(codigo, nombre);
    }

    public NotaVenta getNotaVentaId(Long id) {
        return notaVentaDao.getNotaVentaId(id);
    }

    // Obtiene el ultimo numero de proveedor final y lo itera con el forma de numero de notaVenta
    public String getSiguienteNumeroConsumidorFinal() {
        String numero = notaVentaDao.getNumeroUltimoConsumidorFinal();

        String ceros = "";
        Integer num = Integer.parseInt(numero);
        numero = String.valueOf(num + 1);
        num = 9 - numero.length();

        for(int i = 0; i < num; i++) {
            ceros += "0";
        }

        return ceros + numero;
    }
}
