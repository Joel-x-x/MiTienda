package desk.mitienda.view;

import desk.mitienda.controller.ProductoController;
import desk.mitienda.model.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProductoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private ProductoController productoController;
	private DefaultTableModel modelo;
	private JTable table;
	private GenerarFrameInterfaz frame;

	private void listarProductos(String nombre){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<Producto> listaProductos = productoController.listar(null, nombre);
		modelo.addColumn("Id");
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Categoria");
		modelo.addColumn("Precio");
		modelo.addColumn("Utilidad");
		modelo.addColumn("Stock");
		modelo.addColumn("Iva");

		listaProductos.forEach(producto -> modelo.addRow(new Object[]{
				producto.getId(),
				producto.getCodigo(),
				producto.getNombre(),
				producto.getDescripcion(),
				producto.getCategoria(),
				Double.parseDouble(producto.getPrecioVenta().toString()),
				producto.getUtilidad(),
				producto.getStock(),
				producto.getIva()
		}));

	}

	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	public void productoSeleccionado(Long id) {
		Producto producto = productoController.getProductoId(id);

		frame.objetoSeleccionadoProducto(producto);
	}

	public ProductoFrame(GenerarFrameInterfaz frame) {
		this.frame = frame;
		// Controllers
		productoController = new ProductoController();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1020, 600);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1004, 561);
		contentPane.add(panel);
		
		JLabel lblClientes = new JLabel("Productoes");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setFont(new Font("Jockey One", Font.BOLD, 28));
		lblClientes.setBounds(10, 28, 984, 37);
		panel.add(lblClientes);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por nombre:");
		lblNewLabel_1_4_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(10, 94, 99, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textNombre = new JTextField();
		textNombre.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listarProductos(textNombre.getText());
			}
		});
		textNombre.setColumns(10);
		textNombre.setBounds(119, 88, 137, 25);
		panel.add(textNombre);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(0, 0, 0));
		btnBuscar.setBounds(266, 88, 89, 25);
		panel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(new Color(0, 0, 0));
		btnLimpiar.setBounds(365, 88, 89, 25);
		btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLimpiar.setText("");
				listarProductos(null);
			}
		});
		panel.add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 127, 984, 423);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Long id = (Long) table.getValueAt(table.getSelectedRow(), 0);
				productoSeleccionado(id);
				dispose();
			}
		});
		scrollPane.setViewportView(table);

		listarProductos(null);
	}

}
