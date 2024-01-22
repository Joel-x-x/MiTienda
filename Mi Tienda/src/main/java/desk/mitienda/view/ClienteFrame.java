package desk.mitienda.view;

import desk.mitienda.controller.ClienteController;
import desk.mitienda.model.Cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private DefaultTableModel modelo;
	private GenerarFrameInterfaz frame;
	private ClienteController clienteController;
	private JTable table;
	private Long clienteId;

	private void listarClientes(String nombre){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<Cliente> listaClientes = clienteController.listar(null,nombre);

		modelo.addColumn("Id");
		modelo.addColumn("Identificación");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Celular");

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

	public void clienteSeleccionado() {
		Cliente cliente = clienteController.getClienteId(clienteId);

		frame.objetoSeleccionadoProveedorCliente((Cliente) cliente);
		this.dispose();
	}


	public ClienteFrame(GenerarFrameInterfaz frame) {
		this.frame = frame;

		// Controllers
		clienteController = new ClienteController();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1020, 600);
		setLocationRelativeTo(null);
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
		
		JLabel lblClientes = new JLabel("CLIENTES");
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(119, 88, 137, 25);
		panel.add(textField);
		
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
				clienteId = (Long) table.getValueAt(table.getSelectedRow(), 0);

				clienteSeleccionado();
			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

		// Listar
		listarClientes(null);
	}

}
