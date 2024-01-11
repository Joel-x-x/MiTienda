package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacion;
    private String nombre;
    private String apellido;
    private String celular;
    private Boolean estado;


    public void desactivar() {
        this.estado = false;
    }
}
