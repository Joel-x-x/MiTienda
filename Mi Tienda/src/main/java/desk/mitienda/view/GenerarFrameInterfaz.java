package desk.mitienda.view;

import desk.mitienda.model.Producto;

public interface GenerarFrameInterfaz {
    public void objetoSeleccionadoProveedorCliente(Object object);
    public void objetoSeleccionadoProducto(Producto producto);
    public void listarProveedorCliente();
    public void listarProductos();
}
