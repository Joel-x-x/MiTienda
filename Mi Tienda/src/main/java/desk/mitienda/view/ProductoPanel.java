package desk.mitienda.view;

import desk.mitienda.controller.CategoriaController;
import desk.mitienda.controller.IvaController;
import desk.mitienda.controller.ProductoController;
import desk.mitienda.model.Categoria;
import desk.mitienda.model.Iva;
import desk.mitienda.model.Producto;
import desk.mitienda.model.Proveedor;
import desk.mitienda.utils.Estado;


import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ProductoPanel extends JPanel {
	private JTextField txt_usuario;
	private JTextField txt_nombres;
	private JButton btn_agregar_usuario;
	private JComboBox <Categoria> comboBoxCategoria;
	private DefaultComboBoxModel<Categoria> comboBoxModelCategoria = new DefaultComboBoxModel<>();
	private JButton btn_modificar;
	private JButton btn_eliminar;
	private JButton btn_limpiar_formulario;
	private JButton btn_buscar;
	private JButton btn_limpiar_lista;
	private JTextField txt_busqueda_usuarios;
	private JTable table;
	private JTextField txt_utilidades;
	private JLabel labelIva;
	private DefaultComboBoxModel <Iva> comboBoxModelIva = new DefaultComboBoxModel<>();
	private JTextField txt_nombre_producto;
	private JCheckBox chbx_tiene_iva;
	private ProductoController productoController;
	private Long productoId;
	private int columna;
	private int row;
	private DefaultTableModel modelo;

	private CategoriaController categoriaController;
	private IvaController ivaController;

	//-------------------------------------Utilidades--------------------------------
	public void iniciarCombos(){
		//Combo Categoria
		comboBoxModelCategoria.removeAllElements();
		comboBoxModelCategoria.addAll(categoriaController.listar());
		comboBoxCategoria.setModel(comboBoxModelCategoria);
		comboBoxCategoria.setSelectedIndex(0);

		//Combo Iva
		Iva iva = ivaController.ultimoIva();
		labelIva.setText(iva.getIva().toString());

	}
	//	Llenar datos
	/**
	 * @return Llena los campos de producto
	 */
	public Producto llenarProducto(){

		Producto producto = new Producto(); // Instancia de Producto utilizando el constructor predeterminado

		producto.setCodigo(txt_usuario.getText());
		producto.setNombre(txt_nombre_producto.getText());
		producto.setDescripcion(txt_nombres.getText());
		producto.setCategoria((Categoria) comboBoxCategoria.getSelectedItem());
		producto.setUtilidad(BigDecimal.valueOf(Double.parseDouble(txt_utilidades.getText())));
		producto.setTieneIva(chbx_tiene_iva.isSelected());
		producto.setEstado(true);

		if(chbx_tiene_iva.isSelected())
			producto.setIva(ivaController.ultimoIva());

		return producto;
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

		txt_usuario.setText(producto.getCodigo());
		txt_nombre_producto.setText(producto.getNombre());
		txt_nombres.setText(producto.getDescripcion());
		comboBoxCategoria.setSelectedItem(producto.getCategoria());
		txt_utilidades.setText(String.valueOf(producto.getUtilidad()));
		chbx_tiene_iva.setSelected(producto.getTieneIva());

	}

	private void activarBotones() {
		btn_agregar_usuario.setEnabled(true);
		btn_eliminar.setEnabled(true);
		btn_modificar.setEnabled(true);
		btn_limpiar_formulario.setEnabled(true);

	}



	private void listarProductos(String codigo, String nombre){
		modelo = (DefaultTableModel) table.getModel();
		List<Producto> listaProductos = productoController.listar(codigo, nombre);
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

	public void limpiarFormulario(){
		txt_usuario.setText("");
		comboBoxCategoria.setSelectedItem(null);
		txt_nombres.setText("");
		txt_nombre_producto.setText("");
		txt_utilidades.setText("");
		chbx_tiene_iva.setSelected(true);
		checkIva();
	}

	public void checkIva() {
		if(chbx_tiene_iva.isSelected())
			labelIva.setText(ivaController.ultimoIva().toString() + "%");
		else
			labelIva.setText("0%");
	}

	public void limpiarLista(){
		borrarDatosTabla();
		listarProductos(null, null);
	}


//----------------------------------------------------Metodos Botones----------------------------------------------------------

	public void agregar(){
		Producto producto = llenarProducto();
		Estado estado = productoController.guardar(producto);

		if(estado.getExito()){
			productoId = producto.getId();


			JOptionPane.showMessageDialog(null, estado.getMensaje());
			limpiarFormulario();
			borrarDatosTabla();
			listarProductos(null, null);

		}else{
			JOptionPane.showMessageDialog(null, estado.getMensaje());

		}
	}

	public void actualizar(){

		Producto producto = llenarProducto();
		producto.setId(productoId);
		Estado estado = productoController.actualizar(producto);
		if(estado.getExito()){
			JOptionPane.showMessageDialog(null, estado.getMensaje());

			borrarDatosTabla();
			listarProductos(null, null);
			limpiarFormulario();
			bloquearBotones();
		} else {
			JOptionPane.showMessageDialog(null, estado.getMensaje());

		}

	}

	public void eliminar(){
		Estado estado = productoController.eliminar(productoId);

		if(estado.getExito()){
			JOptionPane.showMessageDialog(null,estado.getMensaje());
			limpiarFormulario();
			bloquearBotones();
			borrarDatosTabla();
			listarProductos(null, null);
		}else{
			JOptionPane.showMessageDialog(null,estado.getMensaje());
		}
	}

	public void buscarCodigo(){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<Producto> listaProductos = productoController.listar(txt_busqueda_usuarios.getText(), null);
		modelo.addColumn("1");
		modelo.addColumn("2");
		modelo.addColumn("3");
		modelo.addColumn("4");
		modelo.addColumn("5");
		modelo.addColumn("6");
		modelo.addColumn("7");


		String[] cabeceras = {"Id","Codigo", "Nombre", "Descripcion", "Categoria", "Tiene Iva", "Iva"};
		modelo.addRow(cabeceras);
		listaProductos.forEach(producto -> modelo.addRow(new Object[]{
				producto.getId(),
				producto.getCodigo(),
				producto.getNombre(),
				producto.getDescripcion(),
				producto.getCategoria(),
				producto.getTieneIva(),
				producto.getIva()
		}));


	}


//-----------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Create the panel.
	 * @param panelAlto 
	 * @param panelAncho 
	 */
	public ProductoPanel(int panelAncho, int panelAlto) {
		iniciarCombos();

		productoController = new ProductoController();
		categoriaController = new CategoriaController();
		ivaController = new IvaController();

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
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(22, 189, 169, 28);
		add(comboBoxCategoria);
		
		JLabel lblRol = new JLabel("Categoria ");
		lblRol.setForeground(Color.WHITE);
		lblRol.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblRol.setBorder(null);
		lblRol.setBounds(32, 151, 114, 38);
		add(lblRol);
		
		btn_agregar_usuario = new JButton("Agregar");
		btn_agregar_usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();

			}
		});
		btn_agregar_usuario.setForeground(Color.WHITE);
		btn_agregar_usuario.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_agregar_usuario.setBorder(null);
		btn_agregar_usuario.setBackground(Color.BLACK);
		btn_agregar_usuario.setBounds(25, 246, 100, 28);
		add(btn_agregar_usuario);
		
		btn_modificar = new JButton("Modificar");
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();

			}
		});
		btn_modificar.setForeground(Color.WHITE);
		btn_modificar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_modificar.setBorder(null);
		btn_modificar.setBackground(Color.BLACK);
		btn_modificar.setBounds(135, 246, 100, 28);
		add(btn_modificar);
		
		btn_eliminar = new JButton("Eliminar");
		btn_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();

			}
		});
		btn_eliminar.setForeground(Color.WHITE);
		btn_eliminar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_eliminar.setBorder(null);
		btn_eliminar.setBackground(Color.BLACK);
		btn_eliminar.setBounds(245, 246, 100, 28);
		add(btn_eliminar);
		
		btn_limpiar_formulario = new JButton("Limpiar");
		btn_limpiar_formulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
			}
		});
		btn_limpiar_formulario.setForeground(Color.WHITE);
		btn_limpiar_formulario.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_limpiar_formulario.setBorder(null);
		btn_limpiar_formulario.setBackground(Color.BLACK);
		btn_limpiar_formulario.setBounds(355, 246, 100, 28);
		add(btn_limpiar_formulario);
		
		btn_buscar = new JButton("Buscar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCodigo();
			}
		});
		btn_buscar.setForeground(Color.WHITE);
		btn_buscar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_buscar.setBorder(null);
		btn_buscar.setBackground(Color.BLACK);
		btn_buscar.setBounds(297, 307, 100, 28);
		add(btn_buscar);
		
		btn_limpiar_lista = new JButton("Limpiar");
		btn_limpiar_lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarLista();
				txt_busqueda_usuarios.setText("");
			}
		});
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
		scrollPane.setBounds(10, 355, 1040, 354);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				columna = table.getSelectedColumn();
				row = table.getSelectedRow();
				productoId = (Long) table.getValueAt(row,0);

				activarBotones();
				llenarFormulario();
				btn_agregar_usuario.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);
		
		txt_utilidades = new JTextField();
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
		chbx_tiene_iva.setSelected(true);
		chbx_tiene_iva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIva();
			}
		});
		add(chbx_tiene_iva);
		
		JLabel lblIva = new JLabel("IVA ");
		lblIva.setForeground(Color.WHITE);
		lblIva.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblIva.setBorder(null);
		lblIva.setBounds(493, 151, 114, 38);
		add(lblIva);
		
		labelIva = new JLabel();
		labelIva.setBounds(493, 189, 169, 28);
		labelIva.setForeground(Color.WHITE);
		labelIva.setFont(new Font("Jockey One", Font.PLAIN, 14));
		labelIva.setBorder(null);
		add(labelIva);
		
		txt_nombre_producto = new JTextField();
		txt_nombre_producto.setColumns(10);
		txt_nombre_producto.setBounds(201, 107, 169, 28);
		add(txt_nombre_producto);

		listarProductos(null, null);
		iniciarCombos();
		bloquearBotones();
		checkIva();

	}
	
	
}
