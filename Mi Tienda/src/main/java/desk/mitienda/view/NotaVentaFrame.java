package desk.mitienda.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import desk.mitienda.controller.*;
import desk.mitienda.model.*;
import desk.mitienda.utils.Utilidades;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NotaVentaFrame extends JFrame implements GenerarFrameInterfaz, GenerarFormularioProductoInterfaz {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCliente;
	private JTextField textPropietario;
	private JTextField textFormaPago;
	private NotaVenta notaVenta;
	private Cliente cliente;
	private NotaVentaController notaVentaController;
	private KardexController kardexController;
	private ClienteController clienteController;
	private DatosEmpresaController datosEmpresaController;
	private CajaController cajaController;
	private JLabel labelCliente;
	private JLabel labelTotal;
	private JTable table;
	private DefaultTableModel modelo;
	private String codigoProducto;

	public void actualizarValoresNotaVenta() {
		notaVenta.actualizarValoresCompra();
		labelTotal.setText(notaVenta.getTotal() + "");
	}

	//	Formulario para actualizar cantidad y precioUnitario detalle y actualizar total, iva y subtaotal con cada detalle.
	private void listarDetalles() {
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<DetalleNota> listaDetalles = notaVenta.getDetalle();
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio");
		modelo.addColumn("Total");

		listaDetalles.forEach(detalle -> modelo.addRow(new Object[]{
				detalle.getProducto().getCodigo(),
				detalle.getProducto().getNombre(),
				detalle.getCantidad(),
				detalle.getPrecio(),
				detalle.getTotal()
		}));

	}

	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	public void eliminarDetalle() {
		Iterator<DetalleNota> iterator = notaVenta.getDetalle().iterator();

		while(iterator.hasNext()) {
			DetalleNota detalle = iterator.next();

			if(detalle.getProducto().getCodigo().equals(codigoProducto)) {
				iterator.remove();
			}
		}

		actualizarValoresNotaVenta();
		listarDetalles();
	}

	public void guardar() {
		notaVentaController.guardar(notaVenta);
		kardexController.registrarKardexVenta(notaVenta);
		// Agregar monto en caja
		Caja caja = cajaController.getCajaAbiertaUsuarioId(Utilidades.getUsuario().getId());
		caja.agregarValor(notaVenta.getTotal());
		cajaController.actualizar(caja);

		this.dispose();
	}

	@Override
	public void objetoSeleccionadoProveedorCliente(Object object) {
		cliente = (Cliente) object;

		notaVenta.setCliente(cliente);
		textCliente.setText(cliente.getIdentificacion());
		labelCliente.setText(cliente.getNombre() + " " + cliente.getApellido());
	}

	@Override
	public void objetoSeleccionadoProducto(Producto producto) {
		DetalleNota detalleNota = new DetalleNota(producto);
		notaVenta.agregarDetalle(detalleNota);
		actualizarValoresNotaVenta();
		listarDetalles();
	}

	@Override
	public void listarProveedorCliente() {
		new ClienteFrame(this);
	}

	@Override
	public void listarProductos() {
		new ProductoFrame(this);
	}

	// Formulario producto
	@Override
	public void actualizarDetalle(Double cantidad, BigDecimal precio) {

		notaVenta.getDetalle().forEach(detalle -> {
			if(detalle.getProducto().getCodigo() == codigoProducto) {
				if(cantidad != null) {
					detalle.setCantidad(cantidad);
				}

				if(precio != null) {
					detalle.setPrecio(precio);
				}

				detalle.recalcular();
			}
		});

		listarDetalles();
		actualizarValoresNotaVenta();
	}

	@Override
	public void desplegarFormulario() {
		new DetalleCompraFrame(this);
	}

	@Override
	public Object getObject() {
		return null;
	}

	public NotaVentaFrame() {
		// Controllers
		notaVentaController = new NotaVentaController();
		kardexController = new KardexController();
		clienteController = new ClienteController();
		datosEmpresaController = new DatosEmpresaController();
		cajaController = new CajaController();

		notaVenta = new NotaVenta(notaVentaController.getSiguienteNumeroConsumidorFinal());

		setBounds(new Rectangle(0, 0, 1080, 800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 640);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(43, 45, 49));
		panel.setBounds(0, 0, 1105, 867);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFactura = new JLabel("Nota de venta");
		lblFactura.setForeground(Color.WHITE);
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(0, 0, 1064, 46);
		panel.add(lblFactura);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Cliente");
		lblNewLabel_1_4_2.setForeground(Color.WHITE);
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2.setBounds(20, 63, 50, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textCliente = new JTextField();
		textCliente.setForeground(new Color(0, 64, 128));
		textCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		textCliente.setEditable(false);
		textCliente.setColumns(10);
		textCliente.setBounds(69, 57, 150, 25);
		panel.add(textCliente);
		
		JButton btnBuscarCliente = new JButton("Buscar un cliente");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarProveedorCliente();
			}
		});
		btnBuscarCliente.setForeground(Color.WHITE);
		btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarCliente.setFocusPainted(false);
		btnBuscarCliente.setBorder(null);
		btnBuscarCliente.setBackground(Color.BLACK);
		btnBuscarCliente.setBounds(20, 99, 199, 30);
		panel.add(btnBuscarCliente);
		
		JLabel lblNewLabel_1_4_2_1 = new JLabel("Nombre:");
		lblNewLabel_1_4_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1.setBounds(20, 158, 50, 14);
		panel.add(lblNewLabel_1_4_2_1);
		
		labelCliente = new JLabel("Pedro");
		labelCliente.setForeground(new Color(255, 255, 255));
		labelCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelCliente.setBounds(69, 140, 350, 46);
		panel.add(labelCliente);
		
		JLabel lblNewLabel_1_4_2_3 = new JLabel("No Factura");
		lblNewLabel_1_4_2_3.setForeground(Color.WHITE);
		lblNewLabel_1_4_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2_3.setBounds(658, 57, 77, 14);
		panel.add(lblNewLabel_1_4_2_3);
		
		JLabel labelEstablecimiento = new JLabel("");
		labelEstablecimiento.setForeground(Color.GRAY);
		labelEstablecimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelEstablecimiento.setBounds(729, 39, 70, 46);
		panel.add(labelEstablecimiento);
		
		JLabel labelNumeroFactura = new JLabel("000000001");
		labelNumeroFactura.setForeground(Color.WHITE);
		labelNumeroFactura.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelNumeroFactura.setBounds(814, 39, 250, 46);
		panel.add(labelNumeroFactura);
		
		JLabel Establecimiento = new JLabel("Establecimiento");
		Establecimiento.setForeground(Color.WHITE);
		Establecimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento.setBounds(658, 98, 99, 14);
		panel.add(Establecimiento);
		
		textPropietario = new JTextField();
		textPropietario.setEditable(false);
		textPropietario.setDragEnabled(true);
		textPropietario.setColumns(10);
		textPropietario.setBounds(751, 93, 250, 25);
		panel.add(textPropietario);
		
		JLabel Establecimiento_1 = new JLabel("Forma de pago");
		Establecimiento_1.setForeground(Color.WHITE);
		Establecimiento_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1.setBounds(658, 140, 99, 14);
		panel.add(Establecimiento_1);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(752, 551, 129, 46);
		panel.add(lblTotal);
		
		labelTotal = new JLabel("0.0");
		labelTotal.setForeground(new Color(255, 255, 255));
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTotal.setBounds(891, 551, 129, 46);
		panel.add(labelTotal);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(46, 56, 64));
		btnGuardar.setBounds(20, 560, 100, 30);
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		panel.add(btnGuardar);
		
		textFormaPago = new JTextField();
		textFormaPago.setEditable(false);
		textFormaPago.setDragEnabled(true);
		textFormaPago.setColumns(10);
		textFormaPago.setBounds(751, 135, 250, 25);
		panel.add(textFormaPago);
		
		JLabel Establecimiento_1_1_1 = new JLabel("Fecha");
		Establecimiento_1_1_1.setForeground(Color.WHITE);
		Establecimiento_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1_1_1.setBounds(658, 181, 50, 14);
		panel.add(Establecimiento_1_1_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(751, 176, 250, 25);
		panel.add(dateChooser);
		
		JLabel lblNewLabel_1_4_2_1_1 = new JLabel("Vendedor:");
		lblNewLabel_1_4_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_4_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1_1.setBounds(20, 510, 50, 14);
		panel.add(lblNewLabel_1_4_2_1_1);
		
		JLabel labelVendedor = new JLabel("Juan");
		labelVendedor.setForeground(new Color(255, 255, 255));
		labelVendedor.setBackground(new Color(255, 255, 255));
		labelVendedor.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelVendedor.setBounds(80, 492, 350, 46);
		panel.add(labelVendedor);
		
		JButton btnBuscarProducto = new JButton("Buscar producto");
		btnBuscarProducto.setForeground(Color.WHITE);
		btnBuscarProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarProducto.setFocusPainted(false);
		btnBuscarProducto.setBorder(null);
		btnBuscarProducto.setBackground(Color.BLACK);
		btnBuscarProducto.setBounds(20, 197, 199, 25);
		btnBuscarProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listarProductos();
			}
		});
		panel.add(btnBuscarProducto);
		
		JButton btnActualizar = new JButton("Actualizar producto");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setEnabled(false);
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(Color.BLACK);
		btnActualizar.setBounds(229, 197, 199, 25);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desplegarFormulario();
			}
		});
		panel.add(btnActualizar);
		
		JButton btnEliminarProducto = new JButton("Eliminar");
		btnEliminarProducto.setForeground(Color.WHITE);
		btnEliminarProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminarProducto.setFocusPainted(false);
		btnEliminarProducto.setEnabled(false);
		btnEliminarProducto.setBorder(null);
		btnEliminarProducto.setBackground(Color.BLACK);
		btnEliminarProducto.setBounds(438, 197, 100, 25);
		btnEliminarProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarDetalle();
			}
		});
		panel.add(btnEliminarProducto);

		// Incializar Nota Venta
		notaVenta.setPuntoEmision("001");
		notaVenta.setEstablecimiento("001");
		notaVenta.setFormaPago("Efectivo");
		notaVenta.setFecha(LocalDate.now());
		notaVenta.setUsuario(Utilidades.getUsuario());

		labelEstablecimiento.setText(notaVenta.getPuntoEmision() + " " + notaVenta.getEstablecimiento());
		labelNumeroFactura.setText(notaVenta.getNumero());
		textFormaPago.setText(notaVenta.getFormaPago());
		textPropietario.setText(datosEmpresaController.getDatosEmpresa().getNombres());
		dateChooser.setDate(Date.from(notaVenta.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		labelVendedor.setText(notaVenta.getUsuario().getNombre() + " " + notaVenta.getUsuario().getApellido());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(20, 233, 1030, 248);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				codigoProducto = (String) table.getValueAt(table.getSelectedRow(), 0);
				btnActualizar.setEnabled(true);
				btnEliminarProducto.setEnabled(true);
			}
		});
		scrollPane.setViewportView(table);
	}

}
