package desk.mitienda.view;

import desk.mitienda.controller.ProveedorController;
import desk.mitienda.model.Proveedor;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class ProveerdorPanel extends JPanel {
	private JTextField txt_usuario;
	private JTextField txt_nombres;
	private JTextField txt_apellidos;
	private JButton btn_agregar_usuario;
	private JButton btn_modificar;
	private JButton btn_eliminar;
	private JButton btn_limpiar_formulario;
	private JButton btn_buscar;
	private JButton btn_limpiar_lista;
	private JTextField txt_busqueda_usuarios;
	private JTable table;
	private JTextField txt_celular;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private ProveedorController proveedorController;
	private JTextField textField_3;
	private Long proveedorId;
	private DefaultTableModel modelo;

	//-------------------------------------Utilidades--------------------------------

	//Limpiar formulario


	public void limpiarFormulario(){
		txt_usuario.setText("");
		textField_3.setText("");
		txt_nombres.setText("");
		txt_apellidos.setText("");
		txt_celular.setText("");
		textField.setText("");
		textField_1.setText("");
	}


	//	Llenar datos
	/**
	 * @return Llena los campos de proveedor
	 */
	public Proveedor llenarProveedor(){
		return Proveedor.builder()
				.identificacion(txt_usuario.getText())
				.razonSocial(textField_3.getText())
				.empresa(txt_nombres.getText())
				.direccion(txt_apellidos.getText())
				.celular(txt_celular.getText())
				.correo(textField.getText())
				.descripcion(textField_1.getText())
				.estado(true)
				.build();

	}


	// Bloquear botones

	private void bloquearBotones() {
		btn_eliminar.setEnabled(false);
		btn_modificar.setEnabled(false);
	}


	private void listarProveedores(){
		modelo = (DefaultTableModel) table.getModel();
		List <Proveedor> listaproveedores = proveedorController.listar(null, null);
		System.out.println(listaproveedores.get(0).getRazonSocial());
		modelo.addColumn("1");
		modelo.addColumn("2");
		modelo.addColumn("3");
		modelo.addColumn("4");
		modelo.addColumn("5");
		modelo.addColumn("6");
		modelo.addColumn("7");
		modelo.addColumn("8");

		String[] cabeceras = {"Id","Identifiación", "Razon Social", "Empresa", "Dirección", "Celular", "Correo", "Descripción"};
		modelo.addRow(cabeceras);
		listaproveedores.forEach(proveedor -> modelo.addRow(new Object[]{
					proveedor.getId(),
					proveedor.getIdentificacion(),
					proveedor.getRazonSocial(),
					proveedor.getEmpresa(),
					proveedor.getDireccion(),
					proveedor.getCelular(),
					proveedor.getCorreo(),
					proveedor.getDescripcion()
			}));
	}

//--------------------------------------Creacion de metodos para botones---------------------------


	//Metodo agregar Proveedor
	public void agregar(){

		//Llenar del proveedor
		Proveedor proveedor = llenarProveedor();
		Estado estado = proveedorController.guardar(proveedor);

		//Validaciones

		if(proveedor.getIdentificacion().isEmpty()){
			JOptionPane.showMessageDialog(null, "El campo de identificación esta vacío");
			return;
		}
		if(proveedor.getEmpresa().isEmpty()){
			JOptionPane.showMessageDialog(null, "El campo de empresa esta vacío");
			return;
		}

		//Agregar
		if(estado.getExito()){
			proveedorId = proveedor.getId();

			JOptionPane.showMessageDialog(null, estado.getMensaje());
			limpiarFormulario();

		}else{
			JOptionPane.showMessageDialog(null, estado.getMensaje());

		}

	}

	// Metodo modificar proveedor

	public void actualizar(){

		//Llenar proveedor
		Proveedor proveedor = llenarProveedor();
		Estado estado = proveedorController.actualizar(proveedor);

		if(estado.getExito()){
			JOptionPane.showMessageDialog(null, "Modificado con Exito!");
			listarProveedores();
			limpiarFormulario();
			bloquearBotones();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo modificar");

		}
	}
//------------------------------------------------------------------------------------------
	/**
	 * Create the panel.
	 */
	public ProveerdorPanel(int panelAncho, int panelAlto) {

		proveedorController = new ProveedorController();
		setBackground(new Color(49, 51, 56));
		setLayout(null);
		setPreferredSize(new Dimension(panelAncho, panelAlto));

		JLabel lblUsuarios = new JLabel("Proveedores");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setForeground(Color.WHITE);
		lblUsuarios.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblUsuarios.setBounds(10, 11, 196, 47);
		add(lblUsuarios);

		JLabel lblUsuario = new JLabel("Identificación");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblUsuario.setBorder(null);
		lblUsuario.setBounds(32, 69, 114, 38);
		add(lblUsuario);

		txt_usuario = new JTextField();
		txt_usuario.setBounds(22, 107, 169, 28);
		add(txt_usuario);
		txt_usuario.setColumns(10);

		JLabel lblClave = new JLabel("Razon Social");
		lblClave.setForeground(Color.WHITE);
		lblClave.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblClave.setBorder(null);
		lblClave.setBounds(201, 69, 114, 38);
		add(lblClave);

		JLabel lblNombre = new JLabel("Empresa");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblNombre.setBorder(null);
		lblNombre.setBounds(380, 69, 114, 38);
		add(lblNombre);

		txt_nombres = new JTextField();
		txt_nombres.setColumns(10);
		txt_nombres.setBounds(380, 107, 169, 28);
		add(txt_nombres);

		JLabel lblApellidos = new JLabel("Dirección");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblApellidos.setBorder(null);
		lblApellidos.setBounds(559, 69, 114, 38);
		add(lblApellidos);

		txt_apellidos = new JTextField();
		txt_apellidos.setColumns(10);
		txt_apellidos.setBounds(559, 107, 169, 28);
		add(txt_apellidos);

		JLabel lblRol = new JLabel("Celular");
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
		btn_buscar.setBounds(397, 307, 100, 28);
		add(btn_buscar);

		btn_limpiar_lista = new JButton("Limpiar");
		btn_limpiar_lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_limpiar_lista.setForeground(Color.WHITE);
		btn_limpiar_lista.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_limpiar_lista.setBorder(null);
		btn_limpiar_lista.setBackground(Color.BLACK);
		btn_limpiar_lista.setBounds(507, 307, 100, 28);
		add(btn_limpiar_lista);

		JLabel lblBuscarPorUsuario = new JLabel("Buscar por identifiación");
		lblBuscarPorUsuario.setForeground(Color.WHITE);
		lblBuscarPorUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorUsuario.setBorder(null);
		lblBuscarPorUsuario.setBounds(10, 274, 155, 38);
		add(lblBuscarPorUsuario);

		txt_busqueda_usuarios = new JTextField();
		txt_busqueda_usuarios.setColumns(10);
		txt_busqueda_usuarios.setBounds(10, 309, 169, 28);
		add(txt_busqueda_usuarios);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 355, 860, 354);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txt_celular = new JTextField();
		txt_celular.setColumns(10);
		txt_celular.setBounds(22, 189, 169, 28);
		add(txt_celular);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblCorreo.setBorder(null);
		lblCorreo.setBounds(201, 151, 114, 38);
		add(lblCorreo);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(201, 189, 169, 28);
		add(textField);

		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setForeground(Color.WHITE);
		lblDescripcin.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblDescripcin.setBorder(null);
		lblDescripcin.setBounds(380, 151, 114, 38);
		add(lblDescripcin);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(380, 189, 169, 28);
		add(textField_1);

		JLabel lblBuscarPorEmpresa = new JLabel("Buscar por empresa");
		lblBuscarPorEmpresa.setForeground(Color.WHITE);
		lblBuscarPorEmpresa.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorEmpresa.setBorder(null);
		lblBuscarPorEmpresa.setBounds(190, 274, 155, 38);
		add(lblBuscarPorEmpresa);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(189, 307, 169, 28);
		add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(201, 107, 169, 28);
		add(textField_3);

		listarProveedores();

	}


}
