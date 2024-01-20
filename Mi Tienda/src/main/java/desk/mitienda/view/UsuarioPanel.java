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
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class UsuarioPanel extends JPanel {
	private JTextField txt_usuario;
	private JPasswordField txt_psw_contraseña;
	private JTextField txt_nombres;
	private JTextField txt_apellidos;
	private JButton btn_agregar_usuario;
	private JComboBox comboBox_rol;
	private JButton btn_modificar;
	private JButton btn_eliminar;
	private JButton btn_limpiar_formulario;
	private JButton btn_buscar;
	private JButton btn_limpiar_lista;
	private JTextField txt_busqueda_usuarios;
	private JTable table;
	private DefaultTableModel modelo;
	private UsuarioController usuarioController;
	private Long usuarioId;

	public void listar() {
		modelo = (DefaultTableModel) table.getModel();

		List<Usuario> usuarios = usuarioController.listar();
		modelo.addColumn("Id");
		modelo.addColumn("Usuario");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Rol");

		usuarios.forEach(usuario -> modelo.addRow(new Object[]{
				usuario.getId(),
				usuario.getUsuario(),
				usuario.getNombre(),
				usuario.getApellido(),
				usuario.getRol()
		}));
	}

	public void agregar() {
		Usuario usuario = llenarUsuario();
		Estado estado = usuarioController.registrar(usuario);

		borrarDatosTabla();
		listar();
		JOptionPane.showMessageDialog(null, estado.getMensaje());
	}

	public void modificar() {
		Usuario usuario = llenarUsuario();
		usuario.setId(usuarioId);
		Estado estado = usuarioController.actualizar(usuario);

		JOptionPane.showMessageDialog(null, estado.getMensaje());
		listar();
		limpiarFormulario();
		borrarDatosTabla();
	}

	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	public void llenarFormulario() {
		Usuario usuario = usuarioController.getUsuarioId(usuarioId);

		txt_nombres.setText(usuario.getNombre());
		txt_apellidos.setText(usuario.getApellido());
		txt_usuario.setText(usuario.getUsuario());
		txt_psw_contraseña.setText(usuario.getClave());
		comboBox_rol.setSelectedItem(usuario.getRol());
	}

	public Usuario llenarUsuario() {
		return Usuario.builder()
				.nombre(txt_nombres.getText())
				.apellido(txt_apellidos.getText())
				.estado(true)
				.usuario(txt_usuario.getText())
				.clave(String.valueOf(txt_psw_contraseña.getPassword()))
				.rol((Rol) comboBox_rol.getSelectedItem())
				.build();
	}

	public void limpiarFormulario() {
		txt_usuario.setText("");
		txt_nombres.setText("");
		txt_apellidos.setText("");
		txt_psw_contraseña.setText("");
	}
	/**
	 * Create the panel.
	 */
	public UsuarioPanel(int panelAncho, int panelAlto) {
		// Controllers
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
		comboBox_rol.setModel(new DefaultComboBoxModel(new Rol[] {Rol.VENDEDOR, Rol.BODEGUERO}));
		comboBox_rol.setBounds(22, 189, 169, 28);
		add(comboBox_rol);

		JLabel lblRol = new JLabel("Rol");
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
		btn_agregar_usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		add(btn_agregar_usuario);

		btn_modificar = new JButton("Modificar");
		btn_modificar.setForeground(Color.WHITE);
		btn_modificar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_modificar.setBorder(null);
		btn_modificar.setBackground(Color.BLACK);
		btn_modificar.setBounds(135, 246, 100, 28);
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				listar();
			}
		});
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

				usuarioId = (Long) table.getValueAt(table.getSelectedRow(),0);

				llenarFormulario();
				btn_agregar_usuario.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);

		// Listar
		listar();

	}


}
