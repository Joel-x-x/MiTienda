package desk.mitienda.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import desk.mitienda.controller.CompraController;
import desk.mitienda.controller.DatosEmpresaController;
import desk.mitienda.controller.KardexController;
import desk.mitienda.model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompraFrame extends JFrame implements GenerarFrameInterfaz, GenerarFormularioProductoInterfaz {
	private JPanel contentPane;
	private JTextField textProveedor;
	private JTextField textNombrePropietario;
	private JTable table;
	private JTextField textPuntoEmision;
	private JTextField textNumero;
	private JTextField textEstablecimiento;
	private JDateChooser dateChooser;
	private JTextField textFormaPago;
	private Proveedor proveedor;
	private Compra compra;
	private DefaultTableModel modelo;
	private JButton btnActualizar;
	private String codigoDetalle;
	private JLabel labelSubtotal;
	private JLabel labelIVA;
	private JLabel labelTotal;
	private DatosEmpresaController datosEmpresaController;
	private CompraController compraController;
	private KardexController kardexController;

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

	public void actualizarValoresCompra() {
		compra.actualizarValoresCompra();
		labelSubtotal.setText(compra.getSubtotal() + "");
		labelIVA.setText(compra.getIva() + "");
		labelTotal.setText(compra.getTotal() + "");
	}

	public void eliminarDetalle() {
		Iterator<DetalleCompra> iterador = compra.getDetalle().iterator();

		while(iterador.hasNext()) {
			DetalleCompra detalle = iterador.next();
			if(detalle.getProducto().getCodigo().equals(codigoDetalle)) {
				iterador.remove();
			}

		}


		compra.actualizarValoresCompra();
		actualizarValoresCompra();
		listarDetalles();
	}

	public void guardarCompra() {
		compra.setPuntoEmision(textPuntoEmision.getText());
		compra.setEstablecimiento(textEstablecimiento.getText());
		compra.setNumero(textNumero.getText());
		compra.setFecha(dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		compra.setTieneProveedor(true);
		compra.setFormaPago(textFormaPago.getText());

		compraController.guardar(compra);
		// Kardex
		kardexController.registrarKardexCompra(compra);
		this.dispose();
	}

	@Override
	public void objetoSeleccionadoProveedorCliente(Object object) {
		proveedor = (Proveedor) object;
		compra.setProveedor(proveedor);

		textProveedor.setText(proveedor.getEmpresa());
	}

	@Override
	public void objetoSeleccionadoProducto(Producto producto) {
		DetalleCompra detalleCompra = new DetalleCompra(producto);
		compra.agregarDetalle(detalleCompra);
		actualizarValoresCompra();
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

	// Formulario producto

	@Override
	public void actualizarDetalle(Double cantidad, BigDecimal precio) {

		compra.getDetalle().forEach(detalle -> {
			if(detalle.getProducto().getCodigo() == codigoDetalle) {
				if(cantidad != null) {
					detalle.setCantidad(cantidad);
				}

				if(precio != null) {
					detalle.setPrecioUnitario(precio);
				}

				detalle.recalcular();
			}
		});

		listarDetalles();
		actualizarValoresCompra();
	}

	@Override
	public void desplegarFormulario() {
		new DetalleCompraFrame(this);
	}

	@Override
	public Object getObject() {
		return this.compra;
	}

	/**
	 * Create the frame.
	 */
	public CompraFrame() {
		compra = new Compra();
		compra.setFecha(LocalDate.now());
		datosEmpresaController = new DatosEmpresaController();
		compraController = new CompraController();
		kardexController = new KardexController();

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
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarDetalle();
				btnEliminarProducto.setEnabled(true);
			}
		});
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				codigoDetalle = (String) table.getValueAt(table.getSelectedRow(), 0);

				btnActualizar.setEnabled(true);
				btnEliminarProducto.setEnabled(true);
			}
		});
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
		
		labelSubtotal = new JLabel("0.0");
		labelSubtotal.setForeground(Color.WHITE);
		labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSubtotal.setBounds(935, 576, 129, 46);
		panel.add(labelSubtotal);
		
		labelIVA = new JLabel("0.0");
		labelIVA.setForeground(Color.WHITE);
		labelIVA.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelIVA.setBounds(935, 618, 129, 46);
		panel.add(labelIVA);
		
		labelTotal = new JLabel("0.0");
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
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarCompra();
			}
		});
		panel.add(btnGuardar);
		
		JLabel Establecimiento_2 = new JLabel("Punto de emisión");
		Establecimiento_2.setForeground(Color.WHITE);
		Establecimiento_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_2.setBounds(10, 60, 99, 14);
		panel.add(Establecimiento_2);
		
		textPuntoEmision = new JTextField();
		textPuntoEmision.setDragEnabled(true);
		textPuntoEmision.setColumns(10);
		textPuntoEmision.setBounds(103, 55, 50, 25);
		panel.add(textPuntoEmision);
		
		JLabel Establecimiento_3 = new JLabel("Número compra");
		Establecimiento_3.setForeground(Color.WHITE);
		Establecimiento_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_3.setBounds(10, 90, 99, 14);
		panel.add(Establecimiento_3);
		
		textNumero = new JTextField();
		textNumero.setDragEnabled(true);
		textNumero.setColumns(10);
		textNumero.setBounds(103, 85, 250, 25);
		panel.add(textNumero);
		
		JLabel Establecimiento_2_1 = new JLabel("Establecimiento");
		Establecimiento_2_1.setForeground(Color.WHITE);
		Establecimiento_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_2_1.setBounds(163, 60, 99, 14);
		panel.add(Establecimiento_2_1);
		
		textEstablecimiento = new JTextField();
		textEstablecimiento.setDragEnabled(true);
		textEstablecimiento.setColumns(10);
		textEstablecimiento.setBounds(256, 55, 50, 25);
		panel.add(textEstablecimiento);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(750, 139, 250, 25);
		panel.add(dateChooser);
		
		textFormaPago = new JTextField();
		textFormaPago.setEditable(false);
		textFormaPago.setDragEnabled(true);
		textFormaPago.setColumns(10);
		textFormaPago.setBounds(750, 101, 250, 25);
		panel.add(textFormaPago);
		
		btnActualizar = new JButton("Actualizar producto");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setBorder(null);
		btnActualizar.setEnabled(false);
		btnActualizar.setBackground(Color.BLACK);
		btnActualizar.setBounds(219, 197, 199, 25);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desplegarFormulario();
			}
		});
		panel.add(btnActualizar);

		// Inicializar compra
		dateChooser.setDate(Date.from(compra.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		textNombrePropietario.setText(datosEmpresaController.getDatosEmpresa().getNombres());
		textFormaPago.setText("Efectivo");
	}

}
