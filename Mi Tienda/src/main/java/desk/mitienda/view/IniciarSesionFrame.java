package desk.mitienda.view;



import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.Utilidades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IniciarSesionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private UsuarioController usuarioController;

	//---------------------------------------------------------------------------------------------------------------------------

	//-------------------------------------------------------Redondear bordes--------------------------------------------------------------------
//	private RoundBorderTextField createTextField() {
//	    RoundBorderTextField textField = new RoundBorderTextField(10, 5); // El segundo parámetro es el radio del borde
//	    textField.setForeground(Color.WHITE);
//	    textField.setFont(new Font("Jockey One", Font.PLAIN, 17));
//	    textField.setColumns(10);
//	    return textField;
//	}
//    private void setRoundBorder(JTextField textField) {
//        int cornerRadius = 10;
//        textField.setBorder(new LineBorder(new Color(64, 66, 73), 5, true) {
//            @Override
//            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
//                super.paintBorder(c, g, x, y, width, height);
//                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g.create();
//                g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
//                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
//                g2d.drawRoundRect(x, y, width - 1, height - 1, cornerRadius, cornerRadius);
//                g2d.dispose();
//            }
//        });
//    }
    private RoundBorderTextField createTextField(int columns, int cornerRadius) {
        return new RoundBorderTextField(columns, cornerRadius);
    }
//---------------------------------------------------------------------------------------------------------------------------
//-------------------------------------Utilidades--------------------------------------------------------------------------------------------
	public Usuario llenarUsuario(){
		return Usuario.builder()
				.usuario(textUsuario.getText())
				.clave(String.valueOf(passwordField.getPassword()))
				. estado(true)
				.build();
	}

	public void login(){
		Usuario usuario = llenarUsuario();
		Estado estado = usuarioController.login(textUsuario.getText(),String.valueOf(passwordField.getPassword()));

		if(estado.getExito()){
			Utilidades.setUsuario((Usuario) estado.getObject()); // Sabemos que usuario se encuentra acutalmente logeado
			new AdminFrame();
			dispose();
		}else{
			JOptionPane.showMessageDialog(null, estado.getMensaje());
		}
	}
//------------------------------------------------------- Constructor --------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public IniciarSesionFrame() {
		usuarioController = new UsuarioController();

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1280, 800);
		contentPane.add(panel);
		panel.setLayout(null);

		RoundBorderPanel panelCampos = new RoundBorderPanel(new Color(43, 45, 49), 60);
		panelCampos.setForeground(new Color(255, 255, 255));
        panelCampos.setBounds(300, 100, 659, 450);
        panelCampos.setLayout(null);
        panel.add(panelCampos);
		
		JLabel lblNewLabel = new JLabel("INICIAR SESIÓN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblNewLabel.setBounds(265, 38, 172, 50);
		panelCampos.add(lblNewLabel);
		
		JLabel lblIniciarSesin = new JLabel("Usuario");
		lblIniciarSesin.setForeground(Color.WHITE);
		lblIniciarSesin.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblIniciarSesin.setBounds(29, 129, 172, 50);
		panelCampos.add(lblIniciarSesin);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblContrasea.setBounds(29, 218, 172, 50);
		panelCampos.add(lblContrasea);
		
		
		
		passwordField =  new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBackground(new Color(64, 66, 73));
		passwordField.setBounds(178, 228, 378, 39);
		passwordField.setText("hola1234@");
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					login();
				}
			}
		});
		panelCampos.add(passwordField);

		textUsuario = createTextField(10, 5);
		textUsuario.setBounds(178, 139, 378, 39);
		textUsuario.setText("hola");
		textUsuario.setFocusable(true);
		panelCampos.add(textUsuario);

		JButton btnBotonOk = new JButton("OK");
		btnBotonOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Cambiar el cursor a un cursor de mano
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restablecer el cursor predeterminado cuando el ratón sale del botón
				e.getComponent().setCursor(Cursor.getDefaultCursor());
			}
		});
		btnBotonOk.setFont(new Font("Jockey One", Font.PLAIN, 25));
		btnBotonOk.setBorderPainted(false);
		btnBotonOk.setBackground(Color.BLACK);
		btnBotonOk.setForeground(Color.WHITE);
		btnBotonOk.setBounds(265, 330, 141, 50);
		btnBotonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		panelCampos.add(btnBotonOk);
		
//		JLabel lblRegistrarse = new JLabel("REGISTRARSE ");
//		lblRegistrarse.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				RegistroUsuarioFrame frame = new RegistroUsuarioFrame();
//				frame.setVisible(true);
//				frame.setLocationRelativeTo(null);
//
//				// Cierra el frame al hacer clic en el label
//				dispose();
//
//			}
//		});
//		lblRegistrarse.setForeground(Color.WHITE);
//		lblRegistrarse.setFont(new Font("Jockey One", Font.PLAIN, 18));
//		lblRegistrarse.setBounds(1087, 710, 172, 50);
//		panel.add(lblRegistrarse);

	}
}
