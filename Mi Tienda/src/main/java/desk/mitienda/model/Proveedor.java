package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
