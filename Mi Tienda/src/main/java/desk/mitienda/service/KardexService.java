package desk.mitienda.service;

import desk.mitienda.controller.ProductoController;
import desk.mitienda.dao.KardexDao;
import desk.mitienda.model.*;
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
            BigDecimal sumaTotal = totalAnterior.add(detalle.getSubtotal()).setScale(4, RoundingMode.HALF_UP);
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
                    .precioTotal(detalle.getSubtotal())
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

    public void registrarNotaVenta(NotaVenta notaVenta) {
        FlyWay.migrate();

        kardexDao = new KardexDao(JPAUtils.getEntityManager());

        notaVenta.getDetalle().forEach(detalle -> {
            // Stock anterior más la cantidad de la compra
            Double cantidadExistencia = detalle.getProducto().getStock() - detalle.getCantidad();
            Producto producto = detalle.getProducto();

            // En venta el precio no cambia por lo tanto sera el mismo
            BigDecimal costoTotalExistencia = producto.getPrecioMedio().multiply(BigDecimal.valueOf(cantidadExistencia));

            Kardex kardex = Kardex.builder()
                    .fecha(LocalDate.now())
                    .numero(notaVenta.getNumero())
                    .proveedorCliente(notaVenta.getCliente().getNombre() + " " + notaVenta.getCliente().getApellido())
                    .tipo(Tipo.SALIDA)
                    .producto(detalle.getProducto())
                    .cantidad(detalle.getCantidad())
                    .precioUnitario(producto.getPrecioMedio())
                    .precioTotal(producto.getPrecioMedio().multiply(BigDecimal.valueOf(detalle.getCantidad()))) // Tomar en cuenta que es el precio sin iva ni utilidad
                    .cantidadExistencia(cantidadExistencia)
                    .costoUnitarioExistencia(producto.getPrecioMedio())
                    .costoTotalExistencia(costoTotalExistencia)
                    .build();

            // Persistencia kardex
            kardexDao.guardar(kardex);

            // Actualizar producto
            productoController = new ProductoController();

            Producto productoMaganed = productoController.getProductoId(producto.getId());

            productoMaganed.setStock(cantidadExistencia);

            productoController.actualizar(productoMaganed);
        });
    }

}
