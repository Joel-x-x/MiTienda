package desk.mitienda.prueba;

import desk.mitienda.controller.CajaController;
import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.Caja;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.FlyWay;

import java.math.BigDecimal;

public class CajaPrueba {
    public static void main(String[] args) {
        FlyWay.migrate();
        CajaController cajaController = new CajaController();
        UsuarioController usuarioController = new UsuarioController();

        Usuario usuario = usuarioController.getUsuarioId(1L);

        // Apertura de caja
//        Caja caja = new Caja();
//
//        caja.abrirCaja(new BigDecimal(100), usuario);
//
//        cajaController.guardar(caja);

        // Cierre de caja
        Caja caja = cajaController.getCajaAbiertaUsuarioId(1L);
        caja.setMontoCierre(new BigDecimal(110));
        caja.calcularDescuadre();
        caja.cerrarCaja();
        cajaController.actualizar(caja);
    }
}
