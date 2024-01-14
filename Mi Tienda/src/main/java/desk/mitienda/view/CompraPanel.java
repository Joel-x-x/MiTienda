package desk.mitienda.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class CompraPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 * @param panelAlto 
	 * @param panelAncho 
	 */
	public CompraPanel(int panelAncho, int panelAlto) {
		
		setBounds(ALLBITS, ABORT, 1080, 800);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1080, 800);
		add(panel);
		panel.setLayout(null);
		//setPreferredSize (new Dimension(panelAncho, panelAlto));
		
		JLabel lblFactura = new JLabel("COMPRA");
		lblFactura.setForeground(new Color(255, 255, 255));
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Jockey One", Font.BOLD, 27));
		lblFactura.setBounds(0, 0, 1060, 46);
		panel.add(lblFactura);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setFocusPainted(false);
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(Color.BLACK);
		btnNuevo.setBounds(27, 103, 100, 30);
		panel.add(btnNuevo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setFocusPainted(false);
		btnModificar.setEnabled(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(Color.BLACK);
		btnModificar.setBounds(137, 103, 100, 30);
		panel.add(btnModificar);
		
		JButton btnEliminar = new JButton("Anular");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setEnabled(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Color.BLACK);
		btnEliminar.setBounds(247, 103, 100, 30);
		panel.add(btnEliminar);
		
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setBorder(null);
		btnRefrescar.setBackground(Color.BLACK);
		btnRefrescar.setBounds(357, 103, 100, 30);
		panel.add(btnRefrescar);
		
		JLabel lblNewLabel_2_1 = new JLabel("Buscar Empresa/Cedula:");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setBounds(750, 107, 160, 22);
		panel.add(lblNewLabel_2_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(887, 106, 140, 25);
		panel.add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 144, 1000, 547);
		panel.add(scrollPane);
		
		JButton btnNewButton_2 = new JButton("Imprimir");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(Color.BLACK);
		btnNewButton_2.setBounds(467, 103, 100, 30);
		panel.add(btnNewButton_2);
		
		JButton btnFormaPago = new JButton("Forma de pago");
		btnFormaPago.setForeground(Color.WHITE);
		btnFormaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFormaPago.setFocusPainted(false);
		btnFormaPago.setEnabled(false);
		btnFormaPago.setBorder(null);
		btnFormaPago.setBackground(Color.BLACK);
		btnFormaPago.setBounds(577, 103, 100, 30);
		panel.add(btnFormaPago);

	}
}
