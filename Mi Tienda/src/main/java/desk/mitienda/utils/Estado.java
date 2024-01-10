package desk.mitienda.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.PublicKey;

@AllArgsConstructor
@Getter
public class Estado {
    private Boolean exito;
    private String mensaje;
    private String stackTrace;

    public Estado(Boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }
}
