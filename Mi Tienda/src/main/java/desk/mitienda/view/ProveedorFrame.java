package desk.mitienda.view;

import desk.mitienda.controller.ProveedorController;
import desk.mitienda.model.Proveedor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class ProveedorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textEmpresa;
	private ProveedorController proveedorController;
	private DefaultTableModel modelo;
	private JTable table;
	private GerarFrameInterfaz frame;

	private void listarProveedores(String empresa){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<Proveedor> listaproveedores = proveedorController.listar(null,empresa);
		modelo.addColumn("Id");
		modelo.addColumn("Identifiación");
		modelo.addColumn("Razon Social");
		modelo.addColumn("Empresa");
		modelo.addColumn("Dirección");
		modelo.addColumn("Celular");
		modelo.addColumn("Correo");
		modelo.addColumn("Descripción");

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

	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	public void proveedorSeleccionado(Long id) {
		Proveedor proveedor = proveedorController.getProveedorId(id);

		frame.objetoSeleccionadoProveedorCliente(proveedor);
	}

	public ProveedorFrame(GerarFrameInterfaz frame) {
		this.frame = frame;
		// Controllers
		proveedorController = new ProveedorController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 600);
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
		
		JLabel lblClientes = new JLabel("Proveedores");
		lblClientes.setForeground(new Color(255, 255, 255));
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setFont(new Font("Jockey One", Font.BOLD, 28));
		lblClientes.setBounds(10, 28, 984, 37);
		panel.add(lblClientes);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por empresa:");
		lblNewLabel_1_4_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(10, 94, 99, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textEmpresa = new JTextField();
		textEmpresa.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listarProveedores(textEmpresa.getText());
			}
		});
		textEmpresa.setColumns(10);
		textEmpresa.setBounds(119, 88, 137, 25);
		panel.add(textEmpresa);
		
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
		btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLimpiar.setText("");
				listarProveedores(null);
			}
		});
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
				Long id = (Long) table.getValueAt(table.getSelectedRow(), 0);
				proveedorSeleccionado(id);
				dispose();
			}
		});
		scrollPane.setViewportView(table);

		listarProveedores(null);
	}

}
