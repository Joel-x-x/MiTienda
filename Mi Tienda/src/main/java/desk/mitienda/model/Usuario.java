package desk.mitienda.model;

import lombok.*;
import javax.persistence.*;
import java.beans.ConstructorProperties;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String usuario;

    private String clave;
    private String nombre;
    private String apellido;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private Boolean estado;

}
