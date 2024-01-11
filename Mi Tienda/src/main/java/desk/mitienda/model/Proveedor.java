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

    private String identificacion;
    private String razonSocial;
    private String empresa;
    private String direccion;
    private String celular;
    private String correo;
    private String descripcion;
    private Boolean estado;

    public void desactivar() {
        this.estado = false;
    }
}
