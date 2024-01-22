package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "detalle_notas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class DetalleNota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cantidad;
    @Column(name = "precio")
    private BigDecimal precio = new BigDecimal(0);
    private BigDecimal total = new BigDecimal(0);
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_venta_id")
    private NotaVenta notaVenta;

    public DetalleNota(Producto producto) {
        this.producto = producto;
        inicializar();
    }

    public void inicializar() {
        this.cantidad = 1.0;
        // Precio de venta
        this.precio = this.producto.getPrecioVenta().setScale(2, RoundingMode.HALF_EVEN);
        this.total = this.precio.multiply(BigDecimal.valueOf(this.cantidad)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public void recalcular() {
        this.total = this.precio.multiply(BigDecimal.valueOf(this.cantidad));
    }
}