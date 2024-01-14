package desk.mitienda.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.TipoMembresiaController;
import com.gym.model.Administrador;

import javax.swing.event.CaretEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TiposMembresiasFrame extends JFrame {
	private int administrador_id;
	private int idSeleccionado;
	private GenerarFrameInterfaz frame;
	private TipoMembresiaController tipoMembresiaController;
	
	private static final long serialVersionUID = -6300901086844090748L;
	private JPanel contentPane;
	private JTextField textBuscar;
	private JTable table;
	private DefaultTableModel modelo;

	private void listarTipoMembresias() {
		String[] cabeceras = {"Id","Nombre", "Descripcion", "Precio", "Duración", "Tipo Duración", "Clase"};
		
		modelo = new DefaultTableModel(tipoMembresiaController.consultar(textBuscar.getText(), administrador_id), cabeceras);
		table.setModel(modelo);
	}
	
	public void tipoMembresiaSeleccionada() {
		frame.tipoMembresiaSeleccionada(idSeleccionado);
		
		this.dispose();
	}

	public TiposMembresiasFrame(GenerarFrameInterfaz frame) {
		this.frame = frame;
		administrador_id = new Administrador().getId();
		tipoMembresiaController = new TipoMembresiaController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1064, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTiposDeMembresias = new JLabel("Productos");
		lblTiposDeMembresias.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiposDeMembresias.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTiposDeMembresias.setBounds(0, 30, 1064, 37);
		panel.add(lblTiposDeMembresias);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por nombre:");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(32, 84, 99, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textBuscar = new JTextField();
		textBuscar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listarTipoMembresias();
			}
		});
		textBuscar.setColumns(10);
		textBuscar.setBounds(141, 78, 137, 25);
		panel.add(textBuscar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(64, 66, 73));
		btnBuscar.setBounds(288, 78, 89, 25);
		panel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(new Color(64, 66, 73));
		btnLimpiar.setBounds(387, 78, 89, 25);
		panel.add(btnLimpiar);
		
		JScrollPane scrollPane_membresias_membresias = new JScrollPane();
		scrollPane_membresias_membresias.setBounds(32, 109, 999, 398);
		panel.add(scrollPane_membresias_membresias);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
				tipoMembresiaSeleccionada();
			}
		});
		scrollPane_membresias_membresias.setViewportView(table);
		
		listarTipoMembresias();
	}
}
