package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "compras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String puntoEmision;
    private String establecimiento;
    private String numero;
    private LocalDate fecha;
    private String formaPago;
    private Double descuento;
    @OneToOne
    private Proveedor proveedor;
    private Double subtotal;
    private Double iva;
    private Double total;

    @OneToMany
    private List<DetalleCompra> detalle;
    private Boolean tieneProveedor;

}
