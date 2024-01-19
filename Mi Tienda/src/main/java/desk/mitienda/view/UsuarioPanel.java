package desk.mitienda.view;

import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.Proveedor;
import desk.mitienda.model.Rol;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class UsuarioPanel extends JPanel {
	private JTextField txt_usuario;
	private JPasswordField txt_psw_contraseña;
	private JTextField txt_nombres;
	private JTextField txt_apellidos;
	private JButton btn_agregar_usuario;
	private JComboBox <Rol> comboBox_rol = new JComboBox<>();
	private DefaultComboBoxModel <Rol> comboBoxRolModel = new DefaultComboBoxModel<>();
	private JButton btn_modificar;
	private JButton btn_eliminar;
	private JButton btn_limpiar_formulario;
	private JButton btn_buscar;
	private JButton btn_limpiar_lista;
	private JTextField txt_busqueda_usuarios;
	private JTable table;
	private UsuarioController usuarioController;
	private Long usuarioId;
	private DefaultTableModel modelo;
	private int row;
	private int columna;
	private Rol rol;
//--------------------------------------------------------------Utilidades---------------------------------------------------------------------
//	Set combobox

	private void setCombo(){
		comboBoxRolModel.removeAllElements();
		comboBoxRolModel.addAll(Collections.singleton(rol));
		comboBox_rol.setModel(comboBoxRolModel);
		comboBox_rol.setSelectedIndex(0);
	}

	/**
	 * @return Llena los campos de proveedor
	 */
	public Usuario llenarUsuario(){
		return Usuario.builder()
				.usuario(txt_usuario.getText())
				.clave(String.valueOf(txt_psw_contraseña.getPassword()))
				.nombre(txt_nombres.getText())
				.apellido(txt_apellidos.getText())
				.rol((Rol) comboBox_rol.getSelectedItem())
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

		// Obtener el id del proveedor seleccionado


		Usuario usuario = usuarioController.getUsuarioId(usuarioId);

		//Llenar cajas de texto
		txt_usuario.setText(usuario.getUsuario());
		txt_psw_contraseña.setText(usuario.getClave());
		txt_nombres.setText(usuario.getNombre());
		txt_apellidos.setText(usuario.getApellido());
		comboBox_rol.setSelectedItem(usuario.getRol());



	}

	private void activarBotones() {
		btn_agregar_usuario.setEnabled(true);
		btn_eliminar.setEnabled(true);
		btn_modificar.setEnabled(true);
		btn_limpiar_formulario.setEnabled(true);

	}

	private void listarUsuarios(){
		modelo = (DefaultTableModel) table.getModel();
		List<Usuario> listaUsuarios = usuarioController.listar(null);
		modelo.addColumn("1");
		modelo.addColumn("2");
		modelo.addColumn("3");
		modelo.addColumn("4");
		modelo.addColumn("5");


		String[] cabeceras = {"Id","Usuario", "Nombre", "Apellido", "Rol"};
		modelo.addRow(cabeceras);
		listaUsuarios.forEach(usuario -> modelo.addRow(new Object[]{
				usuario.getId(),
				usuario.getUsuario(),
				usuario.getNombre(),
				usuario.getApellido(),
				usuario.getRol(),

		}));

	}
	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	public void limpiarFormulario(){
		txt_usuario.setText("");
		txt_nombres.setText("");
		txt_apellidos.setText("");
		txt_psw_contraseña.setText("");
		comboBox_rol.setSelectedItem(null);
	}

	public void limpiarLista(){
		borrarDatosTabla();
		listarUsuarios();
	}

//------------------------------------------------------------Metodos Botones-------------------------------------------------------------
	private void agregar(){
		Usuario usuario = llenarUsuario();
		Estado estado = usuarioController.registrar(usuario);



		if(estado.getExito()){
			JOptionPane.showMessageDialog(null, estado.getMensaje());

		}else{
			JOptionPane.showMessageDialog(null,estado.getMensaje());
		}


	}

	private void actualizar(){

		Usuario usuario = llenarUsuario();
		usuario.setId(usuarioId);

		Estado estado = usuarioController.actualizar(usuario);

		if(estado.getExito()){
			JOptionPane.showMessageDialog(null, estado.getMensaje());

			borrarDatosTabla();
			listarUsuarios();
			limpiarFormulario();
			bloquearBotones();
		} else {
			JOptionPane.showMessageDialog(null, estado.getMensaje());

		}
	}

	 private void buscarNombre(){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		 List<Usuario> listaUsuarios = usuarioController.listar(txt_nombres.getText());
		 modelo.addColumn("1");
		 modelo.addColumn("2");
		 modelo.addColumn("3");
		 modelo.addColumn("4");
		 modelo.addColumn("5");


		 String[] cabeceras = {"Id","Usuario", "Nombre", "Apellido", "Rol"};
		 modelo.addRow(cabeceras);
		 listaUsuarios.forEach(usuario -> modelo.addRow(new Object[]{
				 usuario.getId(),
				 usuario.getUsuario(),
				 usuario.getNombre(),
				 usuario.getApellido(),
				 usuario.getRol(),

		 }));


	}
//------------------------------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------------------------Constructor-------------------------------------------------------------------------------------
	/**
	 * Create the panel.
	 */
	public UsuarioPanel(int panelAncho, int panelAlto) {
		setCombo();
		usuarioController = new UsuarioController();

		setBackground(new Color(49, 51, 56));
		setLayout(null);
		setPreferredSize (new Dimension(panelAncho, panelAlto));
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setForeground(Color.WHITE);
		lblUsuarios.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblUsuarios.setBounds(10, 11, 196, 47);
		add(lblUsuarios);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblUsuario.setBorder(null);
		lblUsuario.setBounds(32, 69, 114, 38);
		add(lblUsuario);

		txt_usuario = new JTextField();
		txt_usuario.setBounds(22, 107, 169, 28);
		add(txt_usuario);
		txt_usuario.setColumns(10);

		JLabel lblClave = new JLabel("Contraseña");
		lblClave.setForeground(Color.WHITE);
		lblClave.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblClave.setBorder(null);
		lblClave.setBounds(201, 69, 114, 38);
		add(lblClave);

		txt_psw_contraseña = new JPasswordField();
		txt_psw_contraseña.setBounds(201, 107, 169, 28);
		add(txt_psw_contraseña);

		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblNombre.setBorder(null);
		lblNombre.setBounds(380, 69, 114, 38);
		add(lblNombre);

		txt_nombres = new JTextField();
		txt_nombres.setColumns(10);
		txt_nombres.setBounds(380, 107, 169, 28);
		add(txt_nombres);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblApellidos.setBorder(null);
		lblApellidos.setBounds(559, 69, 114, 38);
		add(lblApellidos);

		txt_apellidos = new JTextField();
		txt_apellidos.setColumns(10);
		txt_apellidos.setBounds(559, 107, 169, 28);
		add(txt_apellidos);

		comboBox_rol = new JComboBox();
		comboBox_rol.setModel(new DefaultComboBoxModel(new String[] {"Bodeguero ", "Vendedor "}));
		comboBox_rol.setBounds(22, 189, 169, 28);
		add(comboBox_rol);

		JLabel lblRol = new JLabel("Rol");
		lblRol.setForeground(Color.WHITE);
		lblRol.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblRol.setBorder(null);
		lblRol.setBounds(32, 151, 114, 38);
		add(lblRol);

		btn_agregar_usuario = new JButton("Agregar");
		btn_agregar_usuario.addActionListener(new ActionListener() {
			@Override
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
			@Override
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
		btn_eliminar.setForeground(Color.WHITE);
		btn_eliminar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_eliminar.setBorder(null);
		btn_eliminar.setBackground(Color.BLACK);
		btn_eliminar.setBounds(245, 246, 100, 28);
		add(btn_eliminar);

		btn_limpiar_formulario = new JButton("Limpiar");
		btn_limpiar_formulario.addActionListener(new ActionListener() {
			@Override
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
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarNombre();
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
			@Override
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
		scrollPane.setBounds(10, 355, 860, 354);
		add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				columna = table.getSelectedColumn();
				row = table.getSelectedRow();
				usuarioId = (Long) table.getValueAt(row,columna);

				activarBotones();
				llenarFormulario();
				btn_agregar_usuario.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);
		listarUsuarios();
		bloquearBotones();

	}


}
