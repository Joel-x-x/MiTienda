package desk.mitienda.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.security.PublicKey;

@AllArgsConstructor
@Getter
@Builder
public class Estado {
    private Boolean exito;
    private String mensaje;
    private String stackTrace;
    private Object object;

    public Estado(Boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }
}
