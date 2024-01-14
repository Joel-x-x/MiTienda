package desk.mitienda.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class ClienteFrame extends JFrame {
	private int administrador_id, idSeleccionadoUsuario;
	private UsuarioController usuarioController;
	private DefaultTableModel modelo;
	private GenerarFrameInterfaz frame;
	
	private static final long serialVersionUID = 4154039692586232154L;	
	private JPanel contentPane;
	private JTextField textBuscar;
	private JTable tableUsuarios;

	private void listar() {
		String[] cabeceras = {"Id","Nombre","Apellido","Nacimiento","Sexo","Email","Cedula","Dirección","Teléfono"};
		
		modelo = new DefaultTableModel(usuarioController.consultar(administrador_id, textBuscar.getText()), cabeceras);
		tableUsuarios.setModel(modelo);
	}
	
	private void usuarioSeleccionado() {
		idSeleccionadoUsuario = (int) tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),0);
		String nombre = (String) tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),1);
		String apellido = (String) tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),2);
		String cedula = (String) tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),6);
		
		frame.usuarioSeleccionado(
			new Usuario(
			idSeleccionadoUsuario,
			nombre,
			apellido,
			cedula
			)
		); 
		
		this.dispose();
	}
	
	public ClienteFrame(GenerarFrameInterfaz frame) {
		this.frame = frame;
		administrador_id = new Administrador().getId();
		usuarioController = new UsuarioController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1020, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1004, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USUARIOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 28, 984, 37);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por nombre:");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(10, 94, 99, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textBuscar = new JTextField();
		textBuscar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listar();
			}
		});
		textBuscar.setColumns(10);
		textBuscar.setBounds(119, 88, 137, 25);
		panel.add(textBuscar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(46, 56, 64));
		btnBuscar.setBounds(266, 88, 89, 25);
		panel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(new Color(46, 56, 64));
		btnLimpiar.setBounds(365, 88, 89, 25);
		panel.add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 127, 984, 423);
		panel.add(scrollPane);
		
		tableUsuarios = new JTable();
		tableUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				usuarioSeleccionado();
			}
		});
		tableUsuarios.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableUsuarios);
		
		listar();
	}
}
