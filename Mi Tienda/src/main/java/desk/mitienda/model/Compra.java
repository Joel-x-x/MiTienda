package desk.mitienda.model;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "punto_emision")
    private String puntoEmision;
    private String establecimiento;
    private String numero;
    private LocalDate fecha;
    @Column(name = "forma_pago")
    private String formaPago;
    private Double descuento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
    @Column(name = "tiene_proveedor")
    private Boolean tieneProveedor;
    private BigDecimal subtotal = new BigDecimal(0);
    private BigDecimal iva = new BigDecimal(0);
    private BigDecimal total = new BigDecimal(0);

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<DetalleCompra> detalle = new ArrayList<>();

    public void agregarDetalle(DetalleCompra detalleCompra) {
        detalleCompra.setCompra(this);
        this.detalle.add(detalleCompra);
        this.subtotal = this.subtotal.add(detalleCompra.getPrecioUnitario());
        this.iva = this.iva.add(detalleCompra.getIva());
        this.total = this.total.add(detalleCompra.getTotal());
    }

    public void proveedorFinal(String numero) {
        this.setTieneProveedor(false);
        this.setNumero(numero);
    }
}
