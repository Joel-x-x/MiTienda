package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "detalleCompras")
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
    private Double precioUnitario;
    private Double subtotal;
    private Double iva;
    private Double total;
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Compra compra;
}
