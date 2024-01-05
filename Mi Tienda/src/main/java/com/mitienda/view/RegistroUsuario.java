
package com.mitienda.view;

import java.awt.EventQueue;
import java.awt.BasicStroke;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RegistroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegistroUsuario frame = new RegistroUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	private RoundBorderTextField createTextField() {
	    RoundBorderTextField textField = new RoundBorderTextField(10, 5); // El segundo parámetro es el radio del borde
	    textField.setForeground(Color.WHITE);
	    textField.setFont(new Font("Jockey One", Font.PLAIN, 17));
	    textField.setColumns(10);
	    return textField;
	}



    private void setRoundBorder(JTextField textField) {
        int cornerRadius = 10;
        textField.setBorder(new LineBorder(new Color(64, 66, 73), 5, true) {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                super.paintBorder(c, g, x, y, width, height);
                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawRoundRect(x, y, width - 1, height - 1, cornerRadius, cornerRadius);
                g2d.dispose();
            }
        });
    }
    private RoundBorderTextField createTextField(int columns, int cornerRadius) {
        return new RoundBorderTextField(columns, cornerRadius);
    }

	/**
	 * Create the frame.
	 */
	public RegistroUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setForeground(new Color(49, 51, 56));
		panel.setBounds(0, 10, 1280, 790);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(43, 45, 49));
		panel_1.setForeground(new Color(43, 45, 49));
		panel_1.setBounds(300, 50, 700, 600);
		
		
		
		RoundBorderPanel panel_11 = new RoundBorderPanel(new Color(43, 45, 49), 10);
        panel_11.setBounds(300, 50, 700, 600);
        panel_11.setLayout(null);
        panel.add(panel_11);
        
		
		
		JButton btnNewButton = new JButton("LISTO");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Jockey One", Font.PLAIN, 23));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(256, 524, 181, 35);
		panel_11.add(btnNewButton);
		
		JLabel lblTelefono = new JLabel("Contraseña ");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblTelefono.setBounds(10, 354, 357, 47);
		panel_11.add(lblTelefono);
		
		JLabel lblRucPropietario = new JLabel("Usuario");
		lblRucPropietario.setForeground(Color.WHITE);
		lblRucPropietario.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblRucPropietario.setBounds(10, 297, 357, 47);
		panel_11.add(lblRucPropietario);
		
		JLabel lblCorreo = new JLabel("Confirmar Contraseña ");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblCorreo.setBounds(10, 411, 357, 47);
		panel_11.add(lblCorreo);
		
		
		
		
		
		
		
		JLabel lblDireccin = new JLabel("Cédula");
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblDireccin.setBounds(10, 240, 357, 47);
		panel_11.add(lblDireccin);
		
		
		
		JLabel lblNombreDeLa = new JLabel("Apellidos");
		lblNombreDeLa.setForeground(Color.WHITE);
		lblNombreDeLa.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblNombreDeLa.setBorder(null);
		lblNombreDeLa.setBounds(10, 183, 189, 47);
		panel_11.add(lblNombreDeLa);
		
		JLabel lblNombreDeDueo = new JLabel("Nombre");
		lblNombreDeDueo.setForeground(Color.WHITE);
		lblNombreDeDueo.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblNombreDeDueo.setBorder(null);
		lblNombreDeDueo.setBounds(10, 126, 189, 47);
		panel_11.add(lblNombreDeDueo);
		
		
		textField_1 = createTextField(10, 5);
		textField_1.setBounds(291, 411, 357, 35);
		panel_11.add(textField_1);

		textField_2 = createTextField(10, 5);
		textField_2.setBounds(291, 354, 357, 35);
		panel_11.add(textField_2);

		textField_3 = createTextField(10, 5);
		textField_3.setBounds(291, 297, 357, 35);
		panel_11.add(textField_3);

		textField = createTextField(10, 5);
		textField.setBounds(291, 240, 357, 35);
		panel_11.add(textField);

		textField_4 = createTextField(10, 5);
		textField_4.setBounds(291, 183, 357, 35);
		panel_11.add(textField_4);

		textField_5 = createTextField(10, 5);
		textField_5.setBounds(291, 126, 357, 35);
		panel_11.add(textField_5);

		
		
		
        
		JLabel lblNewLabel = new JLabel("REGISTRARSE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblNewLabel.setBounds(195, 21, 357, 75);
		panel_11.add(lblNewLabel);
		
		JLabel lblIniciarSesion = new JLabel("INICIAR SESION ");
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblIniciarSesion.setBorder(null);
		lblIniciarSesion.setBounds(1081, 702, 189, 47);
		panel.add(lblIniciarSesion);
	}
}
