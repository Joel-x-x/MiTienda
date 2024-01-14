package desk.mitienda.view;

import com.gym.model.Usuario;

public interface GenerarFrameInterfaz {
	public void listarUsuarios();
	public void usuarioSeleccionado(Usuario usuario);
	public void listarTipoMembresias();
	public void tipoMembresiaSeleccionada(int id);
}

