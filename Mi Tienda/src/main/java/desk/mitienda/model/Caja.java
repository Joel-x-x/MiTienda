package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cajas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Caja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime apertura;
    @Column(name = "monto_apertura")
    private BigDecimal montoApertura = BigDecimal.ZERO;
    private LocalDateTime cierre;
    @Column(name = "monto_cierre")
    private BigDecimal montoCierre = BigDecimal.ZERO;
    @Column(name = "valor_calculado")
    private BigDecimal valorCalculado = BigDecimal.ZERO;
    private BigDecimal descuadre = BigDecimal.ZERO;
    private Boolean cerrada;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public void abrirCaja(BigDecimal montoApertura, Usuario usuario) {
        this.apertura = LocalDateTime.now();
        this.montoApertura = montoApertura;
        this.valorCalculado = this.valorCalculado.add(this.montoApertura);
        this.cerrada = false;
        this.usuario = usuario;
        this.cierre = LocalDateTime.now();
    }

    public void agregarValor(BigDecimal valor) {
        this.valorCalculado = this.valorCalculado.add(valor);
    }

    public void calcularDescuadre() {
        this.descuadre = this.valorCalculado.subtract(this.montoCierre);
    }

    public void cerrarCaja() {
        this.cierre = LocalDateTime.now();
        this.cerrada = true;
    }
}
