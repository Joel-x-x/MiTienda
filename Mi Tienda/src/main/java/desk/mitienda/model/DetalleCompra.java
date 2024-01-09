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
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private Double iva;
    private Double total;
    @OneToOne
    private Producto producto;
    @ManyToOne
    private Compra compra;


}
