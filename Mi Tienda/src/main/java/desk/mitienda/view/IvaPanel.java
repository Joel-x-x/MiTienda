package desk.mitienda.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IvaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public IvaPanel(int panelAncho, int panelAlto) {
		setBounds(0, 0, 1080, 800);
		setLayout(null);
		setPreferredSize(new Dimension(1117, 545));
		
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(49, 51, 56));
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(285, 0, 436, 413);
		add(contentPane);
		
		JLabel lblHistorialDePrecios = new JLabel("IVAS");
		lblHistorialDePrecios.setForeground(new Color(255, 255, 255));
		lblHistorialDePrecios.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDePrecios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHistorialDePrecios.setBounds(29, 28, 434, 37);
		contentPane.add(lblHistorialDePrecios);
		
//		JList<String> list = new JList<String>((ListModel) null);
		JList<String> list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		list.setBounds(-232, 124, 969, 289);
		contentPane.add(list);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Actulizar IVA:");
		lblNewLabel_1_4_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(39, 92, 79, 14);
		contentPane.add(lblNewLabel_1_4_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(119, 86, 137, 25);
		contentPane.add(textField);
		
		JButton btnActualizar = new JButton("Agregar IVA");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(new Color(46, 56, 64));
		btnActualizar.setBounds(266, 87, 123, 25);
		contentPane.add(btnActualizar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1080, 800);
		add(panel);
		

	}

	
}
