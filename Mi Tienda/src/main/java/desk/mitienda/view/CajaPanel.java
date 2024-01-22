package desk.mitienda.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CajaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public CajaPanel(int panelAncho, int panelAlto) {
		setBackground(new Color(49, 51, 56));
		setBounds(0, 0, 1080, 800);
		setLayout(null);
		setPreferredSize(new Dimension(1117, 545));
		
		JLabel lblHistorialDePrecios = new JLabel("Caja");
		lblHistorialDePrecios.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDePrecios.setForeground(Color.WHITE);
		lblHistorialDePrecios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHistorialDePrecios.setBounds(0, 0, 1117, 37);
		add(lblHistorialDePrecios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(20, 64, 1070, 470);
		add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		

	}
}
