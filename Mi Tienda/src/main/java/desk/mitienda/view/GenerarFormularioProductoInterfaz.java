package desk.mitienda.view;

import java.math.BigDecimal;

public interface GenerarFormularioProductoInterfaz {
    public void actualizarDetalle(Double cantidada, BigDecimal precio);
    public void desplegarFormulario();

    public Object getObject();
}
