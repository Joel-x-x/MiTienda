package desk.mitienda.view;

import desk.mitienda.controller.IvaController;
import desk.mitienda.controller.ProductoController;
import desk.mitienda.model.Categoria;
import desk.mitienda.model.Iva;
import desk.mitienda.model.Producto;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

public class ProductoPanel extends JPanel {
	private JTextField txt_usuario;
	private JTextField txt_nombres;
	private JButton btn_agregar_usuario;
	private JComboBox <Categoria> comboBox_rol;
	private JButton btn_modificar;
	private JButton btn_eliminar;
	private JButton btn_limpiar_formulario;
	private JButton btn_buscar;
	private JButton btn_limpiar_lista;
	private JTextField txt_busqueda_usuarios;
	private JTable table;
	private JTextField txt_utilidades;
	private JComboBox <Iva> Cmb_iva;
	private JTextField txt_nombre_producto;
	private JCheckBox chbx_tiene_iva;
	private ProductoController productoController;
	private Long productoId;
	private int columna;
	private int row;

	//-------------------------------------Utilidades--------------------------------

	//	Llenar datos
	/**
	 * @return Llena los campos de producto
	 */
	public Producto llenarProducto(){
		return Producto.builder()
				.codigo(txt_usuario.getText())
				.nombre(txt_nombre_producto.getText())
				.descripcion(txt_nombres.getText())
				.categoria((Categoria) comboBox_rol.getSelectedItem())
				.utilidad((BigDecimal.valueOf(Double.parseDouble(txt_utilidades.getText()))))
				.tieneIva(chbx_tiene_iva.isSelected())
				.iva((Iva) Cmb_iva.getSelectedItem())
				.estado(true)
				.build();

	}


	// Bloquear botones

	private void bloquearBotones() {
		btn_eliminar.setEnabled(false);
		btn_modificar.setEnabled(false);
		btn_limpiar_formulario.setEnabled(false);



	}

	// Llenar formulario segun Id

	private void llenarFormulario(){

		Producto producto = productoController.getProductoId(productoId);

		//Llenar cajas de texto
//		txt_usuario.setText( producto.getCodigo());
//
//		txt_nombres.setText(proveedor.getEmpresa());
//		txt_apellidos.setText(proveedor.getDireccion());
//		txt_celular.setText(proveedor.getCelular());
//		Cmb_iva.setText(proveedor.getCorreo());
//		txt_nombre_producto.setText(proveedor.getDescripcion());


	}

	private void activarBotones() {
		btn_agregar_usuario.setEnabled(true);
		btn_eliminar.setEnabled(true);
		btn_modificar.setEnabled(true);
		btn_limpiar_formulario.setEnabled(true);

	}

//	private void listarProveedores(){
//		modelo = (DefaultTableModel) table.getModel();
//		List<Proveedor> listaproveedores = proveedorController.listar(null, null);
//		System.out.println(listaproveedores.get(0).getRazonSocial());
//		modelo.addColumn("1");
//		modelo.addColumn("2");
//		modelo.addColumn("3");
//		modelo.addColumn("4");
//		modelo.addColumn("5");
//		modelo.addColumn("6");
//		modelo.addColumn("7");
//		modelo.addColumn("8");
//
//		String[] cabeceras = {"Id","Identifiación", "Razon Social", "Empresa", "Dirección", "Celular", "Correo", "Descripción"};
//		modelo.addRow(cabeceras);
//		listaproveedores.forEach(proveedor -> modelo.addRow(new Object[]{
//				proveedor.getId(),
//				proveedor.getIdentificacion(),
//				proveedor.getRazonSocial(),
//				proveedor.getEmpresa(),
//				proveedor.getDireccion(),
//				proveedor.getCelular(),
//				proveedor.getCorreo(),
//				proveedor.getDescripcion()
//		}));
//
//	}
//	private void borrarDatosTabla() {
//		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
//		modelo.setRowCount(0);
//		modelo.setColumnCount(0);
//	}

//	public void limpiarFormulario(){
//		txt_usuario.setText("");
//		Cmb_iva_3.setText("");
//		txt_nombres.setText("");
//		txt_apellidos.setText("");
//		txt_celular.setText("");
//		Cmb_iva.setText("");
//		txt_nombre_producto.setText("");
//	}

//--------------------------------------------------------------------------------------------------------------
	/**
	 * Create the panel.
	 * @param panelAlto 
	 * @param panelAncho 
	 */
	public ProductoPanel(int panelAncho, int panelAlto) {
		setBackground(new Color(49, 51, 56));
		setLayout(null);
		setPreferredSize (new Dimension(panelAncho, panelAlto));
		
		JLabel lblUsuarios = new JLabel("Productos ");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setForeground(Color.WHITE);
		lblUsuarios.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblUsuarios.setBounds(10, 11, 196, 47);
		add(lblUsuarios);
		
		JLabel lblUsuario = new JLabel("Codigo");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblUsuario.setBorder(null);
		lblUsuario.setBounds(32, 69, 114, 38);
		add(lblUsuario);
		
		txt_usuario = new JTextField();
		txt_usuario.setBounds(22, 107, 169, 28);
		add(txt_usuario);
		txt_usuario.setColumns(10);
		
		JLabel lblClave = new JLabel("Nombre");
		lblClave.setForeground(Color.WHITE);
		lblClave.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblClave.setBorder(null);
		lblClave.setBounds(201, 69, 114, 38);
		add(lblClave);
		
		JLabel lblNombre = new JLabel("Descripcion ");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblNombre.setBorder(null);
		lblNombre.setBounds(380, 69, 114, 38);
		add(lblNombre);
		
		txt_nombres = new JTextField();
		txt_nombres.setColumns(10);
		txt_nombres.setBounds(380, 107, 169, 28);
		add(txt_nombres);
		
		comboBox_rol = new JComboBox();
		comboBox_rol.setBounds(22, 189, 169, 28);
		add(comboBox_rol);
		
		JLabel lblRol = new JLabel("Categoria ");
		lblRol.setForeground(Color.WHITE);
		lblRol.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblRol.setBorder(null);
		lblRol.setBounds(32, 151, 114, 38);
		add(lblRol);
		
		btn_agregar_usuario = new JButton("Agregar");
		btn_agregar_usuario.setForeground(Color.WHITE);
		btn_agregar_usuario.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_agregar_usuario.setBorder(null);
		btn_agregar_usuario.setBackground(Color.BLACK);
		btn_agregar_usuario.setBounds(25, 246, 100, 28);
		add(btn_agregar_usuario);
		
		btn_modificar = new JButton("Modificar");
		btn_modificar.setForeground(Color.WHITE);
		btn_modificar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_modificar.setBorder(null);
		btn_modificar.setBackground(Color.BLACK);
		btn_modificar.setBounds(135, 246, 100, 28);
		add(btn_modificar);
		
		btn_eliminar = new JButton("Eliminar");
		btn_eliminar.setForeground(Color.WHITE);
		btn_eliminar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_eliminar.setBorder(null);
		btn_eliminar.setBackground(Color.BLACK);
		btn_eliminar.setBounds(245, 246, 100, 28);
		add(btn_eliminar);
		
		btn_limpiar_formulario = new JButton("Limpiar");
		btn_limpiar_formulario.setForeground(Color.WHITE);
		btn_limpiar_formulario.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_limpiar_formulario.setBorder(null);
		btn_limpiar_formulario.setBackground(Color.BLACK);
		btn_limpiar_formulario.setBounds(355, 246, 100, 28);
		add(btn_limpiar_formulario);
		
		btn_buscar = new JButton("Buscar");
		btn_buscar.setForeground(Color.WHITE);
		btn_buscar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_buscar.setBorder(null);
		btn_buscar.setBackground(Color.BLACK);
		btn_buscar.setBounds(297, 307, 100, 28);
		add(btn_buscar);
		
		btn_limpiar_lista = new JButton("Limpiar");
		btn_limpiar_lista.setForeground(Color.WHITE);
		btn_limpiar_lista.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_limpiar_lista.setBorder(null);
		btn_limpiar_lista.setBackground(Color.BLACK);
		btn_limpiar_lista.setBounds(407, 307, 100, 28);
		add(btn_limpiar_lista);
		
		JLabel lblBuscarPorUsuario = new JLabel("Buscar por Usuario:");
		lblBuscarPorUsuario.setForeground(Color.WHITE);
		lblBuscarPorUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorUsuario.setBorder(null);
		lblBuscarPorUsuario.setBounds(10, 297, 114, 38);
		add(lblBuscarPorUsuario);
		
		txt_busqueda_usuarios = new JTextField();
		txt_busqueda_usuarios.setColumns(10);
		txt_busqueda_usuarios.setBounds(118, 309, 169, 28);
		add(txt_busqueda_usuarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 355, 860, 354);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				columna = table.getSelectedColumn();
				row = table.getSelectedRow();
				productoId = (Long) table.getValueAt(row,columna);

				activarBotones();
				llenarFormulario();
				btn_agregar_usuario.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);
		
		txt_utilidades = new JPasswordField();
		txt_utilidades.setBounds(201, 189, 169, 28);
		add(txt_utilidades);
		
		JLabel lblUtilidad = new JLabel("Utilidad");
		lblUtilidad.setForeground(Color.WHITE);
		lblUtilidad.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblUtilidad.setBorder(null);
		lblUtilidad.setBounds(201, 151, 114, 38);
		add(lblUtilidad);
		
		chbx_tiene_iva = new JCheckBox("Tiene IVA");
		chbx_tiene_iva.setBounds(381, 193, 93, 21);
		add(chbx_tiene_iva);
		
		JLabel lblIva = new JLabel("IVA ");
		lblIva.setForeground(Color.WHITE);
		lblIva.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblIva.setBorder(null);
		lblIva.setBounds(493, 151, 114, 38);
		add(lblIva);
		
		Cmb_iva = new JComboBox();
		Cmb_iva.setBounds(493, 189, 169, 28);
		add(Cmb_iva);
		
		txt_nombre_producto = new JTextField();
		txt_nombre_producto.setColumns(10);
		txt_nombre_producto.setBounds(201, 107, 169, 28);
		add(txt_nombre_producto);

	}
	
	
}
