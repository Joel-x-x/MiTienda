package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kardex")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Kardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String numero;
    @Column(name = "proveedor_cliente")
    private String proveedorCliente;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private Double cantidad;
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "precio_total")
    private BigDecimal precioTotal;
    @Column(name = "cantidad_existencia")
    private Double cantidadExistencia;
    @Column(name = "costo_unitario_existencia")
    private BigDecimal costoUnitarioExistencia;
    @Column(name = "costo_total_existencia")
    private BigDecimal costoTotalExistencia;
}
