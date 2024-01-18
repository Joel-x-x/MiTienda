package desk.mitienda.controller;

import desk.mitienda.dao.KardexDao;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Kardex;
import desk.mitienda.model.NotaVenta;
import desk.mitienda.service.KardexService;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.JPAUtils;

import java.util.List;

public class KardexController {
    KardexDao kardexDao;
    KardexService kardexService;

    public KardexController() {
        this.kardexDao = new KardexDao(JPAUtils.getEntityManager());
        this.kardexService = new KardexService();
    }

    public void registrarKardexCompra(Compra compra) {
        kardexService.registrarCompra(compra);
    }

    public void registrarKardexVenta(NotaVenta notaVenta) {
        kardexService.registrarNotaVenta(notaVenta);
    }
}
