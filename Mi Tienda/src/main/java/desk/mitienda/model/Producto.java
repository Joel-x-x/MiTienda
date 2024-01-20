package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nombre;
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iva_id")
    private Iva iva;
    @Column(name = "tiene_iva")
    private Boolean tieneIva;
    private BigDecimal utilidad = BigDecimal.ZERO;
    private Double stock = 0.0;
    @Column(name = "ultimo_precio_compra")
    private BigDecimal ultimoPrecioCompra = BigDecimal.ZERO;
    @Column(name = "precio_medio")
    private BigDecimal precioMedio = BigDecimal.ZERO;
    @Column(name = "ultima_fecha_compra")
    private LocalDate ultimaFechaCompra;
    @Column(name = "ultimo_proveedor_compra")
    private String ultimoProveedorCompra;
    private Boolean estado;

    public void desactivar() {
        this.estado = false;
    }

    public BigDecimal getPrecioVenta() {
        BigDecimal utilidad = (this.utilidad.divide(new BigDecimal(100.0), 4, RoundingMode.HALF_UP)
                            .add(new BigDecimal(1.0)));

        BigDecimal iva = (this.iva.getIva().divide(new BigDecimal(100.0), 4, RoundingMode.HALF_UP))
                .add(new BigDecimal(1.0));

        BigDecimal resultado = (this.precioMedio.multiply(utilidad))
                .multiply(iva);

        if(resultado != null)
            return resultado;
        else
            return new BigDecimal(0);
    }
}
