package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_compras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cantidad;
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario = new BigDecimal(0);
    private BigDecimal iva = new BigDecimal(0);
    private BigDecimal subtotal = new BigDecimal(0);
    private BigDecimal total = new BigDecimal(0);
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Compra compra;

    public DetalleCompra(Producto producto) {
        this.producto = producto;
        inicializar();
    }
    public void inicializar() {
        this.cantidad = 1.0;
        this.precioUnitario = this.producto.getPrecioMedio();
        this.subtotal = this.precioUnitario;
        this.iva = this.subtotal.multiply(this.producto.getIva().getIva().divide(new BigDecimal(100.0)));
        this.total = this.subtotal.add(this.iva);
    }

    public void recalcular() {
        this.subtotal = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
        this.iva = this.subtotal.multiply(this.producto.getIva().getIva().divide(new BigDecimal(100.0)));
        this.total = this.subtotal.add(this.iva);
    }
}
