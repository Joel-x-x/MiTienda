package desk.mitienda.view;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IniciarSesionFrame extends JFrame {

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
					IniciarSesionFrame frame = new IniciarSesionFrame();
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

	/**
	 * Create the frame.
	 */
	public IniciarSesionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1280, 800);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		RoundBorderPanel panel_11 = new RoundBorderPanel(new Color(43, 45, 49), 60);
        panel_11.setBounds(300, 100, 659, 450);
        panel_11.setLayout(null);
        panel.add(panel_11);
		
		JLabel lblNewLabel = new JLabel("INICIAR SESIÓN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblNewLabel.setBounds(265, 38, 172, 50);
		panel_11.add(lblNewLabel);
		
		JLabel lblIniciarSesin = new JLabel("Usuario");
		lblIniciarSesin.setForeground(Color.WHITE);
		lblIniciarSesin.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblIniciarSesin.setBounds(29, 129, 172, 50);
		panel_11.add(lblIniciarSesin);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblContrasea.setBounds(29, 218, 172, 50);
		panel_11.add(lblContrasea);
		
		
		
		textField_1 = createTextField(10, 5);
		textField_1.setBounds(178, 228, 378, 39);
		panel_11.add(textField_1);

		textField = createTextField(10, 5);
		textField.setBounds(178, 139, 378, 39);
		panel_11.add(textField);

		
		
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Jockey One", Font.PLAIN, 25));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(265, 330, 141, 50);
		panel_11.add(btnNewButton);
		
		JLabel lblRegistrarse = new JLabel("REGISTRARSE ");
		lblRegistrarse.setForeground(Color.WHITE);
		lblRegistrarse.setFont(new Font("Jockey One", Font.PLAIN, 18));
		lblRegistrarse.setBounds(1087, 710, 172, 50);
		panel.add(lblRegistrarse);

	}
}
