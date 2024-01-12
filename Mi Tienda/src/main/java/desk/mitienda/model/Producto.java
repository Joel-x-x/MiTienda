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
    private Boolean tieneIva;
    private Double utilidad;
    private Double stock;
    private Double ultimoPrecioCompra;
    private Double precioMedio;
    private LocalDate ultimaFechaCompra;
    private String UltimoProveedorCompra;
    private Boolean estado;
}
