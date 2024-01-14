package desk.mitienda.view;

import com.gym.model.Factura;

public interface GenerarFacturaFrameInterfaz {
	
	public void llamarFrame(boolean nuevo);
	
	public void accion(Factura factura);
	
	public void setIdSeleccionado(int idSeleccionado);
	
	public int getIdSeleccionado();
}
