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
    private BigDecimal precioUnitario;
    private BigDecimal iva;
    private BigDecimal total;
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Compra compra;

    public DetalleCompra(Producto producto) {
        this.producto = producto;
    }
    public void inicializar() {
        this.cantidad = 1.0;
        this.precioUnitario = new BigDecimal(this.producto.getPrecioMedio());
        this.iva = new BigDecimal(this.producto.getPrecioMedio() * (this.producto.getIva().getIva() / 100.0));
        this.total = this.precioUnitario.add(this.iva);
    }
}
