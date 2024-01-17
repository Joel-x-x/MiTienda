package desk.mitienda.view;



import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IniciarSesionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
	private UsuarioController usuarioController;






//------------------------------------------------------- MAIN --------------------------------------------------------------------
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
	//---------------------------------------------------------------------------------------------------------------------------

	//-------------------------------------------------------Redondear bordes--------------------------------------------------------------------
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
//---------------------------------------------------------------------------------------------------------------------------
//-------------------------------------Utilidades--------------------------------------------------------------------------------------------
	public Usuario llenarUsuario(){
		return Usuario.builder()
				.usuario(textField.getText())
				.clave(String.valueOf(textField_1.getPassword()))
				. estado(true)
				.build();
	}
	public void login(){
		Usuario usuario = llenarUsuario();
		Estado estado = usuarioController.login(textField.getText(),String.valueOf(textField_1.getPassword()));

		if(estado.getExito()){
			JOptionPane.showMessageDialog(null, estado.getMensaje());

			AdminFrame frame = new AdminFrame();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);

			dispose();
		}else{
			JOptionPane.showMessageDialog(null, estado.getMensaje() + " Resigtrate");

		}
	}
//------------------------------------------------------- Constructor --------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public IniciarSesionFrame() {

		usuarioController = new UsuarioController();
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
		
		
		
		textField_1 =  new JPasswordField();
		textField_1.setBounds(178, 228, 378, 39);
		panel_11.add(textField_1);

		textField = createTextField(10, 5);
		textField.setBounds(178, 139, 378, 39);
		panel_11.add(textField);

		
		
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnNewButton.setFont(new Font("Jockey One", Font.PLAIN, 25));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(265, 330, 141, 50);
		panel_11.add(btnNewButton);
		
		JLabel lblRegistrarse = new JLabel("REGISTRARSE ");
		lblRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistroUsuario frame = new RegistroUsuario();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);

				// Cierra el frame al hacer clic en el label
				dispose();

			}
		});
		lblRegistrarse.setForeground(Color.WHITE);
		lblRegistrarse.setFont(new Font("Jockey One", Font.PLAIN, 18));
		lblRegistrarse.setBounds(1087, 710, 172, 50);
		panel.add(lblRegistrarse);

	}
}
