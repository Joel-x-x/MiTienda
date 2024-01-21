package desk.mitienda.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import desk.mitienda.model.Compra;
import desk.mitienda.model.DetalleCompra;
import desk.mitienda.model.Producto;
import desk.mitienda.model.Proveedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CompraFrame extends JFrame implements GerarFrameInterfaz{
	private JPanel contentPane;
	private JTextField textProveedor;
	private JTextField textNombrePropietario;
	private JTable table;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JDateChooser dateChooser;
	private JTextField textFormaPago;
	private Proveedor proveedor;
	private Compra compra;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompraFrame frame = new CompraFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//	Formulario para actualizar cantidad y precioUnitario detalle y actualizar total, iva y subtaotal con cada detalle.
	private void listarDetalles(){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<DetalleCompra> listaDetalles = compra.getDetalle();
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Cantidad");
		modelo.addColumn("PrecioUnitario");
		modelo.addColumn("Subtotal");
		modelo.addColumn("Iva");
		modelo.addColumn("Total");

		listaDetalles.forEach(detalle -> modelo.addRow(new Object[]{
				detalle.getProducto().getCodigo(),
				detalle.getProducto().getNombre(),
				detalle.getCantidad(),
				detalle.getPrecioUnitario(),
				detalle.getSubtotal(),
				detalle.getIva(),
				detalle.getTotal()
		}));

	}

	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	@Override
	public void objetoSeleccionadoProveedorCliente(Object object) {
		proveedor = (Proveedor) object;

		textProveedor.setText(proveedor.getEmpresa());
	}

	@Override
	public void objetoSeleccionadoProducto(Producto producto) {
		DetalleCompra detalleCompra = new DetalleCompra(producto);
		compra.agregarDetalle(detalleCompra);
		listarDetalles();
	}

	@Override
	public void listarProveedorCliente() {
		new ProveedorFrame(this);
	}

	@Override
	public void listarProductos() {
		new ProductoFrame(this);
	}

	/**
	 * Create the frame.
	 */
	public CompraFrame() {
		compra = new Compra();

		setBounds(new Rectangle(0, 0, 1080, 800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 762);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(43, 45, 49));
		panel.setBounds(0, 0, 1105, 867);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFactura = new JLabel("Compra");
		lblFactura.setForeground(Color.WHITE);
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(0, 0, 1064, 46);
		panel.add(lblFactura);
		
		JLabel Proveedor = new JLabel("Proveedor");
		Proveedor.setForeground(Color.WHITE);
		Proveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		Proveedor.setBounds(21, 132, 72, 14);
		panel.add(Proveedor);
		
		textProveedor = new JTextField();
		textProveedor.setForeground(new Color(0, 64, 128));
		textProveedor.setFont(new Font("Tahoma", Font.BOLD, 14));
		textProveedor.setEditable(false);
		textProveedor.setColumns(10);
		textProveedor.setBounds(103, 125, 208, 25);
		panel.add(textProveedor);
		
		JButton btnBuscarProveedor = new JButton("Buscar proveedor");
		btnBuscarProveedor.setForeground(Color.WHITE);
		btnBuscarProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarProveedor.setFocusPainted(false);
		btnBuscarProveedor.setBorder(null);
		btnBuscarProveedor.setBackground(Color.BLACK);
		btnBuscarProveedor.setBounds(321, 124, 184, 30);
		btnBuscarProveedor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarProveedorCliente();
			}
		});
		panel.add(btnBuscarProveedor);
		
		JLabel labelCliente = new JLabel("");
		labelCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelCliente.setBounds(59, 140, 350, 46);
		panel.add(labelCliente);
		
		JButton btnBuscarProducto = new JButton("Buscar producto");
		btnBuscarProducto.setForeground(Color.WHITE);
		btnBuscarProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarProducto.setFocusPainted(false);
		btnBuscarProducto.setBorder(null);
		btnBuscarProducto.setBackground(Color.BLACK);
		btnBuscarProducto.setBounds(10, 197, 199, 25);
		btnBuscarProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarProductos();
			}
		});
		panel.add(btnBuscarProducto);
		
		JButton btnEliminarProducto = new JButton("Eliminar");
		btnEliminarProducto.setForeground(Color.WHITE);
		btnEliminarProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminarProducto.setFocusPainted(false);
		btnEliminarProducto.setEnabled(false);
		btnEliminarProducto.setBorder(null);
		btnEliminarProducto.setBackground(Color.BLACK);
		btnEliminarProducto.setBounds(428, 197, 100, 25);
		panel.add(btnEliminarProducto);
		
		JLabel Establecimiento = new JLabel("Establecimiento");
		Establecimiento.setForeground(Color.WHITE);
		Establecimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento.setBounds(657, 62, 99, 14);
		panel.add(Establecimiento);
		
		textNombrePropietario = new JTextField();
		textNombrePropietario.setEditable(false);
		textNombrePropietario.setDragEnabled(true);
		textNombrePropietario.setColumns(10);
		textNombrePropietario.setBounds(750, 57, 250, 25);
		panel.add(textNombrePropietario);
		
		JLabel Establecimiento_1 = new JLabel("Forma de pago");
		Establecimiento_1.setForeground(Color.WHITE);
		Establecimiento_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1.setBounds(657, 104, 99, 14);
		panel.add(Establecimiento_1);
		
		JLabel Establecimiento_1_1_1 = new JLabel("Fecha");
		Establecimiento_1_1_1.setForeground(Color.WHITE);
		Establecimiento_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1_1_1.setBounds(657, 144, 50, 14);
		panel.add(Establecimiento_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 233, 1040, 344);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblDescuento_1 = new JLabel("Subtotal");
		lblDescuento_1.setForeground(Color.WHITE);
		lblDescuento_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento_1.setBounds(796, 576, 129, 46);
		panel.add(lblDescuento_1);
		
		JLabel lblDescuento_2 = new JLabel("IVA");
		lblDescuento_2.setForeground(Color.WHITE);
		lblDescuento_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento_2.setBounds(796, 618, 129, 46);
		panel.add(lblDescuento_2);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(796, 662, 129, 46);
		panel.add(lblTotal);
		
		JLabel labelSubtotal = new JLabel("0.0");
		labelSubtotal.setForeground(Color.WHITE);
		labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSubtotal.setBounds(935, 576, 129, 46);
		panel.add(labelSubtotal);
		
		JLabel labelIVA = new JLabel("0.0");
		labelIVA.setForeground(Color.WHITE);
		labelIVA.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelIVA.setBounds(935, 618, 129, 46);
		panel.add(labelIVA);
		
		JLabel labelTotal = new JLabel("0.0");
		labelTotal.setForeground(Color.WHITE);
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTotal.setBounds(935, 662, 129, 46);
		panel.add(labelTotal);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(46, 56, 64));
		btnGuardar.setBounds(10, 678, 100, 30);
		panel.add(btnGuardar);
		
		JLabel Establecimiento_2 = new JLabel("Punto de emisión");
		Establecimiento_2.setForeground(Color.WHITE);
		Establecimiento_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_2.setBounds(10, 60, 99, 14);
		panel.add(Establecimiento_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setDragEnabled(true);
		textField_3.setColumns(10);
		textField_3.setBounds(103, 55, 50, 25);
		panel.add(textField_3);
		
		JLabel Establecimiento_3 = new JLabel("Número compra");
		Establecimiento_3.setForeground(Color.WHITE);
		Establecimiento_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_3.setBounds(10, 90, 99, 14);
		panel.add(Establecimiento_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setDragEnabled(true);
		textField_4.setColumns(10);
		textField_4.setBounds(103, 85, 250, 25);
		panel.add(textField_4);
		
		JLabel Establecimiento_2_1 = new JLabel("Establecimiento");
		Establecimiento_2_1.setForeground(Color.WHITE);
		Establecimiento_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_2_1.setBounds(163, 60, 99, 14);
		panel.add(Establecimiento_2_1);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setDragEnabled(true);
		textField_6.setColumns(10);
		textField_6.setBounds(256, 55, 50, 25);
		panel.add(textField_6);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(750, 139, 250, 25);
		panel.add(dateChooser);
		
		textFormaPago = new JTextField();
		textFormaPago.setEditable(false);
		textFormaPago.setDragEnabled(true);
		textFormaPago.setColumns(10);
		textFormaPago.setEnabled(false);
		textFormaPago.setBounds(750, 101, 250, 25);
		panel.add(textFormaPago);
		
		JButton btnActualizar = new JButton("Actualizar producto");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(Color.BLACK);
		btnActualizar.setBounds(219, 197, 199, 25);
		panel.add(btnActualizar);
	}

}
