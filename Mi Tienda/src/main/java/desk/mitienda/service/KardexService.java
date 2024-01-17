package desk.mitienda.service;

import desk.mitienda.controller.ProductoController;
import desk.mitienda.dao.KardexDao;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Kardex;
import desk.mitienda.model.Producto;
import desk.mitienda.model.Tipo;
import desk.mitienda.utils.FlyWay;
import desk.mitienda.utils.JPAUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class KardexService {
    private ProductoController productoController;
    private KardexDao kardexDao;
    public void registrarCompra(Compra compra) {
        FlyWay.migrate();

        kardexDao = new KardexDao(JPAUtils.getEntityManager());

        compra.getDetalle().forEach(detalle -> {
            // Stock anterior más la cantidad de la compra
            Double cantidadExistencia = detalle.getCantidad() + detalle.getProducto().getStock();
            Producto producto = detalle.getProducto();

            // ((producto.precioUnitario * producto.stock) + precioTotal) / cantidadExistencia
            // (Total anterior + total compra) / cantidadSumaAnteriorMasNueva
            BigDecimal totalAnterior = producto.getPrecioMedio().multiply(BigDecimal.valueOf(producto.getStock())).setScale(4, RoundingMode.HALF_UP);
            BigDecimal sumaTotal = totalAnterior.add(detalle.getTotal()).setScale(4, RoundingMode.HALF_UP);
            BigDecimal precioMedioPonderado = sumaTotal.divide(BigDecimal.valueOf(cantidadExistencia), 4, RoundingMode.HALF_UP);

            BigDecimal costoTotalExistencia = precioMedioPonderado.multiply(BigDecimal.valueOf(cantidadExistencia));

            Kardex kardex = Kardex.builder()
                    .fecha(LocalDate.now())
                    .numero(compra.getNumero())
                    .proveedorCliente(compra.getProveedor().getEmpresa())
                    .tipo(Tipo.ENTRADA)
                    .producto(detalle.getProducto())
                    .cantidad(detalle.getCantidad())
                    .precioUnitario(detalle.getPrecioUnitario())
                    .precioTotal(detalle.getTotal())
                    .cantidadExistencia(cantidadExistencia)
                    .costoUnitarioExistencia(precioMedioPonderado)
                    .costoTotalExistencia(costoTotalExistencia)
                    .build();

            // Persistencia kardex
            kardexDao.guardar(kardex);

            // Actualizar producto
            actualizarDatosProducto(producto.getId(), cantidadExistencia,
                    detalle.getPrecioUnitario(), precioMedioPonderado,
                    LocalDate.now(), compra.getProveedor().getEmpresa());
        });
    }

    private void actualizarDatosProducto(Long id, Double stock,
                                         BigDecimal ultimoPrecioCompra,
                                         BigDecimal precioMedio,
                                         LocalDate ultimaFechaCompra,
                                         String ultimoProveedorCompra) {

        productoController = new ProductoController();

        Producto producto = productoController.getProductoId(id);

        producto.setStock(stock);
        producto.setUltimoPrecioCompra(ultimoPrecioCompra);
        producto.setPrecioMedio(precioMedio);
        producto.setUltimaFechaCompra(ultimaFechaCompra);
        producto.setUltimoProveedorCompra(ultimoProveedorCompra);

        productoController.actualizar(producto);
    }

}
