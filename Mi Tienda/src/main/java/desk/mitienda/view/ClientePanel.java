package desk.mitienda.view;

import desk.mitienda.controller.ClienteController;
import desk.mitienda.model.Cliente;
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
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ClientePanel extends JPanel {
	private JTextField txt_usuario;
	private JTextField txt_nombre;
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
	private ClienteController clienteController;
	private int columna;
	private int row;
	private Long clienteId;
	private DefaultTableModel modelo;

	//-------------------------------------Utilidades--------------------------------

	//Limpiar formulario





	//	Llenar datos
	/**
	 * @return Llena los campos de proveedor
	 */
	public Cliente llenarCliente(){
		return Cliente.builder()
				.identificacion(txt_usuario.getText())
				.nombre(txt_nombre.getText())
				.apellido(txt_nombres.getText())
				.celular(txt_apellidos.getText())
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


		Cliente cliente = clienteController.getClienteId(clienteId);

		//Llenar cajas de texto
		txt_usuario.setText(cliente.getIdentificacion());
		txt_nombre.setText(cliente.getNombre());
		txt_nombres.setText(cliente.getApellido());
		txt_apellidos.setText(cliente.getCelular());



	}

	private void activarBotones() {
		btn_agregar_usuario.setEnabled(true);
		btn_eliminar.setEnabled(true);
		btn_modificar.setEnabled(true);
		btn_limpiar_formulario.setEnabled(true);

	}

	private void listarClientes(){
		modelo = (DefaultTableModel) table.getModel();
		List<Cliente> listaClientes = clienteController.listar(null, null);

		modelo.addColumn("1");
		modelo.addColumn("2");
		modelo.addColumn("3");
		modelo.addColumn("4");
		modelo.addColumn("5");
		modelo.addColumn("6");
		modelo.addColumn("7");
		modelo.addColumn("8");

		String[] cabeceras = {"Id","Identifiación", "Nombre", "Apellido", "Celular"};
		modelo.addRow(cabeceras);
		listaClientes.forEach(cliente -> modelo.addRow(new Object[]{
				cliente.getId(),
				cliente.getIdentificacion(),
				cliente.getNombre(),
				cliente.getApellido(),
				cliente.getCelular()
		}));
	}
	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	public void limpiarFormulario(){
		txt_usuario.setText("");
		txt_nombre.setText("");
		txt_nombres.setText("");
		txt_apellidos.setText("");
	}
	public void limpiarLista(){
		borrarDatosTabla();
		listarClientes();
	}
//------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------Metodos Botones----------------------------------------------------------------------------------------

	private void agregar(){
		Cliente cliente = llenarCliente();
		Estado estado = clienteController.guardar(cliente);

		if(estado.getExito()){
			clienteId = cliente.getId();


			JOptionPane.showMessageDialog(null, estado.getMensaje());
			limpiarFormulario();
			borrarDatosTabla();
			listarClientes();

		}else{
			JOptionPane.showMessageDialog(null, estado.getMensaje());

		}

	}

	private void actualizar(){

		Cliente cliente = llenarCliente();
		Estado estado = clienteController.actualizar(cliente);

		if(estado.getExito()){
			clienteId = cliente.getId();


			JOptionPane.showMessageDialog(null, estado.getMensaje());
			limpiarFormulario();
			borrarDatosTabla();
			listarClientes();

		}else{
			JOptionPane.showMessageDialog(null, estado.getMensaje());

		}
	}

	public void eliminar(){
		Estado estado = clienteController.eliminar(clienteId);

		if(estado.getExito()){
			JOptionPane.showMessageDialog(null,estado.getMensaje());
			limpiarFormulario();
			bloquearBotones();
			borrarDatosTabla();
			listarClientes();
		}else{
			JOptionPane.showMessageDialog(null,estado.getMensaje());
		}
	}

	private void buscarIdentificacion(){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<Cliente> listaClientes = clienteController.listar(txt_busqueda_usuarios.getText(), null);

		modelo.addColumn("1");
		modelo.addColumn("2");
		modelo.addColumn("3");
		modelo.addColumn("4");


		String[] cabeceras = {"Id","Identifiación", "Nombre", "Apellido", "Celular"};
		modelo.addRow(cabeceras);
		listaClientes.forEach(cliente -> modelo.addRow(new Object[]{
				cliente.getId(),
				cliente.getIdentificacion(),
				cliente.getNombre(),
				cliente.getApellido(),
				cliente.getCelular()
		}));
	}

//---------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Create the panel.
	 * @param panelAlto 
	 * @param panelAncho 
	 */
	public ClientePanel(int panelAncho, int panelAlto) {
		clienteController = new ClienteController();
		setPreferredSize (new Dimension(panelAncho, panelAlto));
		setBackground(new Color(49, 51, 56));
		setLayout(null);
		
		
		JLabel lblUsuarios = new JLabel("Clientes");
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
		
		JLabel lblClave = new JLabel("Nombre");
		lblClave.setForeground(Color.WHITE);
		lblClave.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblClave.setBorder(null);
		lblClave.setBounds(201, 69, 114, 38);
		add(lblClave);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(201, 107, 169, 28);
		add(txt_nombre);
		
		JLabel lblNombre = new JLabel("Apellidos");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblNombre.setBorder(null);
		lblNombre.setBounds(380, 69, 114, 38);
		add(lblNombre);
		
		txt_nombres = new JTextField();
		txt_nombres.setColumns(10);
		txt_nombres.setBounds(380, 107, 169, 28);
		add(txt_nombres);
		
		JLabel lblApellidos = new JLabel("Celular");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblApellidos.setBorder(null);
		lblApellidos.setBounds(559, 69, 114, 38);
		add(lblApellidos);
		
		txt_apellidos = new JTextField();
		txt_apellidos.setColumns(10);
		txt_apellidos.setBounds(559, 107, 169, 28);
		add(txt_apellidos);
		
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
				buscarIdentificacion();
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
		scrollPane.setBounds(10, 355, 860, 354);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				columna = table.getSelectedColumn();
				row = table.getSelectedRow();
				clienteId = (Long) table.getValueAt(row,columna);

				activarBotones();
				llenarFormulario();
				btn_agregar_usuario.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);

		listarClientes();
		bloquearBotones();
	}
}
