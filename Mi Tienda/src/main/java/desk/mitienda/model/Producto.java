package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;
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
    private Double utilidad;
    private Double stock;
    @Column(name = "ultimo_precio_compra")
    private Double ultimoPrecioCompra;
    @Column(name = "precio_medio")
    private Double precioMedio;
    @Column(name = "ultima_fecha_compra")
    private LocalDate ultimaFechaCompra;
    @Column(name = "ultimo_proveedor_compra")
    private String ultimoProveedorCompra;
    private Boolean estado;

    public void desactivar() {
        this.estado = false;
    }
}
