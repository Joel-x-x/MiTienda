package xd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegistroFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroFrame frame = new RegistroFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistroFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1084, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1068, 615);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(43, 45, 49));
		panel_1.setBounds(204, 48, 659, 450);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIAR SESIÓN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 24));
		lblNewLabel.setBounds(265, 38, 172, 50);
		panel_1.add(lblNewLabel);
		
		JLabel lblIniciarSesin = new JLabel("Usuario");
		lblIniciarSesin.setForeground(Color.WHITE);
		lblIniciarSesin.setFont(new Font("Impact", Font.PLAIN, 20));
		lblIniciarSesin.setBounds(29, 129, 172, 50);
		panel_1.add(lblIniciarSesin);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Impact", Font.PLAIN, 20));
		lblContrasea.setBounds(29, 218, 172, 50);
		panel_1.add(lblContrasea);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBorder(null);
		textField.setBackground(new Color(64, 66, 73));
		textField.setBounds(178, 139, 378, 39);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.WHITE);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(64, 66, 73));
		textField_1.setBounds(178, 228, 378, 39);
		panel_1.add(textField_1);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(265, 330, 141, 50);
		panel_1.add(btnNewButton);
		
		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setForeground(Color.WHITE);
		lblRegistrarse.setFont(new Font("Impact", Font.PLAIN, 20));
		lblRegistrarse.setBounds(940, 554, 172, 50);
		panel.add(lblRegistrarse);
	}
}
