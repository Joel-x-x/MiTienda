package desk.mitienda.model;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ivas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Iva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double iva;
    @CreationTimestamp
    private LocalDate fecha;

}
