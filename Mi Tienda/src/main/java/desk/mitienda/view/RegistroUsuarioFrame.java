
package desk.mitienda.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;

import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.Rol;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;

import javax.swing.event.CaretEvent;

public class RegistroUsuarioFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField txt_confirmar_contraseña;
	private JPasswordField txt_contraseña;
	private JTextField txt_usuario;
	private JTextField txt_cedula;
	private JTextField txt_apellido;
	private JTextField txt_nombre;
	private JLabel lbl_verificador_contraseña;
	private JLabel lbl_coincidencia_contraseñas;
	private UsuarioController usuarioController;
	private Estado estado;
	private int usuario_id;

	private RoundBorderTextField createTextField() {
	    RoundBorderTextField textField = new RoundBorderTextField(10, 5); // El segundo parámetro es el radio del borde
	    textField.setForeground(Color.WHITE);
	    textField.setFont(new Font("Jockey One", Font.PLAIN, 17));
	    textField.setColumns(10);
	    return textField;
	}
	private RoundBorderPasswordField createPasswordField() {
	    RoundBorderPasswordField passwordField = new RoundBorderPasswordField(10, 5); // El segundo parámetro es el radio del borde
	    passwordField.setForeground(Color.WHITE);
	    passwordField.setFont(new Font("Jockey One", Font.PLAIN, 17));
	    passwordField.setColumns(10);
	    return passwordField;
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



    private void setRoundBorder(JPasswordField passwordField) {
        int cornerRadius = 10;
        passwordField.setBorder(new LineBorder(new Color(64, 66, 73), 5, true) {
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
    private RoundBorderPasswordField createPasswordField(int columns, int cornerRadius) {
        return new RoundBorderPasswordField(columns, cornerRadius);
    }
    /**
	 * Create the frame.
	 */


	public RegistroUsuarioFrame() {
		FlyWay.migrate();
		usuarioController = new UsuarioController();


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();

			}
		});
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


		txt_confirmar_contraseña = createPasswordField(10,5);


		txt_confirmar_contraseña.setBounds(291, 411, 357, 35);
		panel_11.add(txt_confirmar_contraseña);

		txt_contraseña = createPasswordField(10,5);
		txt_contraseña.setBounds(291, 354, 357, 35);
		panel_11.add(txt_contraseña);
		txt_contraseña.setText("2");

		txt_contraseña.addCaretListener(new CaretListener() {
			String contraseña= "";
        	String contraseña_array [];

			public void caretUpdate(CaretEvent e) {
				contraseña = String.valueOf(txt_contraseña.getPassword());
        		contraseña_array = new String[contraseña.length()];

        		for(int i = 0; i<contraseña.length(); i++) {
    				contraseña_array[i] = contraseña.substring(i,i+1);
    			}
        		if(contraseña.length()>=8) {
        			boolean mayusculas = false ;
        			for(int i = 0; i<contraseña.length(); i++) {
        				if(contraseña.contains(contraseña_array[i].toUpperCase())) {
        					mayusculas = true;
        				}
        			}

        			if(mayusculas) {
        				if(contraseña.matches(".*\\d.*")) {
        					if(contraseña.matches(".*[^a-zA-Z0-9\\s].*")) {
        						lbl_verificador_contraseña.setForeground(Color.GREEN);
        						lbl_verificador_contraseña.setText("Contraseña Valida");
        					}else {
        						lbl_verificador_contraseña.setForeground(Color.RED);
        	        			lbl_verificador_contraseña.setText("Minimo un caracter especial(*,?,+,-,_,....)");
        	        		}
        				}else {
        					lbl_verificador_contraseña.setForeground(Color.RED);
                			lbl_verificador_contraseña.setText("Minimo un número");
                		}
					}else {
						lbl_verificador_contraseña.setForeground(Color.RED);
	        			lbl_verificador_contraseña.setText("Minimo una Mayúscula");
	        		}

        		}else {
        			lbl_verificador_contraseña.setForeground(Color.RED);
        			lbl_verificador_contraseña.setText("Minimo 8 caracteres");
        		}

			}
		});



		txt_confirmar_contraseña.addCaretListener(new CaretListener() {


			public void caretUpdate(CaretEvent e) {
				if(!String.valueOf(txt_confirmar_contraseña.getPassword()).equals(String.valueOf(txt_contraseña.getPassword()))) {
                	lbl_coincidencia_contraseñas.setText("Sin coincidencias");
                	lbl_coincidencia_contraseñas.setForeground(Color.RED);
                }else if(String.valueOf(txt_confirmar_contraseña.getPassword()).equals(String.valueOf(txt_contraseña.getPassword()))){
                	lbl_coincidencia_contraseñas.setText("");
                }

			}
		});
		txt_usuario = createTextField(10, 5);
		txt_usuario.setBounds(291, 297, 357, 35);
		panel_11.add(txt_usuario);

		txt_cedula = createTextField(10, 5);
		txt_cedula.setBounds(291, 240, 357, 35);
		panel_11.add(txt_cedula);


		txt_apellido = createTextField(10, 5);
		txt_apellido.setBounds(291, 183, 357, 35);
		panel_11.add(txt_apellido);



		txt_nombre = createTextField(10, 5);
		txt_nombre.setBounds(291, 126, 357, 35);
		panel_11.add(txt_nombre);






		JLabel lblNewLabel = new JLabel("REGISTRARSE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblNewLabel.setBounds(195, 21, 357, 75);
		panel_11.add(lblNewLabel);

		JLabel lblIniciarSesion = new JLabel("INICIAR SESION ");
		lblIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IniciarSesionFrame frame = new IniciarSesionFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);

		    // Cierra el frame al hacer clic en el label
				dispose();

			}
		});
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblIniciarSesion.setBorder(null);
		lblIniciarSesion.setBounds(1081, 702, 189, 47);
		panel.add(lblIniciarSesion);

		lbl_verificador_contraseña= new JLabel("New label");
		lbl_verificador_contraseña.setBounds(1010, 414, 217, 14);
		panel.add(lbl_verificador_contraseña);

		lbl_coincidencia_contraseñas = new JLabel("New label");
		lbl_coincidencia_contraseñas.setBounds(1010, 462, 217, 14);
		panel.add(lbl_coincidencia_contraseñas);
	}

	public void registrar() {

		Usuario usuario = llenarUsuario();


		// Validaciones
		if(usuario.getNombre().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo nombre no puede ir vacio");
			return;
		}

//		if(!Utilidades.validarEmail(txt_usuario.getText())) {
//			JOptionPane.showMessageDialog(null, "El campo email no puede ir vacio o no es valido");
//			return;
//		}

		if(usuario.getClave().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo contraseña no puede ir vacio");
			return;
		}



		// Registrar
		estado = usuarioController.registrar(usuario);
		if(estado.getExito()) {


			JOptionPane.showMessageDialog(null, estado.getMensaje());
			RegistroTiendaFrame registroTiendaFrame = new RegistroTiendaFrame();
			registroTiendaFrame.setLocationRelativeTo(null);
			registroTiendaFrame.setVisible(true);
			dispose();




		} else {
			JOptionPane.showMessageDialog(null, estado.getMensaje());
		}
	}
	 public Usuario llenarUsuario() {

		 return Usuario.builder()
			.usuario(txt_usuario.getText())
			.clave(new String(txt_contraseña.getPassword()))
			.nombre(txt_nombre.getText())
			.apellido(txt_apellido.getText())
			.rol(Rol.ADMINISTRADOR)
			.estado(true)
			.build();
	    }
}
