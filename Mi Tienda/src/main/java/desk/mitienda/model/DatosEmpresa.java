package desk.mitienda.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "datos_empresa")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class DatosEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String nombreEmpresa;
    private String nombres;
    private String direccion;
    private String celular;
    private String correo;
    private Boolean estado;
    private String identificacion;




}
