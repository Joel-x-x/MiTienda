package desk.mitienda.controller;

import desk.mitienda.dao.CompraDao;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Compra;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class CompraController {
    CompraDao compraDao;

    public CompraController() {
        this.compraDao = new CompraDao(JPAUtils.getEntityManager());
    }

    public Estado guardar(Compra compra) {
        return compraDao.guardar(compra);
    }

    public List<Compra> listar(String numero, String empresa) {
        return compraDao.listar(numero, empresa);
    }

    public Compra getCompraId(Long id) {
        return compraDao.getCompraId(id);
    }

    // Obtiene el ultimo numero de proveedor final y lo itera con el forma de numero de compra
    public String getSiguienteNumeroProveedorFinal() {
        String numero = compraDao.getNumeroUltimoProveedorFinal();

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
