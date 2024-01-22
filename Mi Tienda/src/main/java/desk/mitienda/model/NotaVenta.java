package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nota_ventas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class NotaVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    @Column(name = "punto_emision")
    private String puntoEmision;
    private String establecimiento;
    private LocalDate fecha;
    @Column(name = "forma_pago")
    private String formaPago;
    private Double descuento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "tiene_cliente")
    private Boolean tieneCliente;
    @OneToMany(mappedBy = "notaVenta", cascade = CascadeType.ALL)
    private List<DetalleNota> detalle = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    private BigDecimal total = new BigDecimal(0);

    // Iniciar un nueva Nota de Venta
    public NotaVenta(String numero) {
        this.numero = numero;
        this.puntoEmision = "001";
        this.establecimiento = "001";
        this.fecha = LocalDate.now();
        this.formaPago = "Efectivo";
        this.descuento = 0.0;
        this.tieneCliente = true;
    }

    public void agregarDetalle(DetalleNota detalleNota) {
        detalleNota.setNotaVenta(this); // Agrega la referencia notaVenta al detalle
        this.detalle.add(detalleNota);
        this.total = this.total.add(detalleNota.getTotal()); // Sumando del total
    }

    public void actualizarValoresCompra() {
        this.total = BigDecimal.ZERO;

        detalle.forEach(detalleCompra -> {
            this.total = this.total.add(detalleCompra.getTotal()); // Sumando del total
        });
    }

    public void consumidorFinal() {
        this.setTieneCliente(false);
    }
}
