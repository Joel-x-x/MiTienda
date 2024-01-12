package desk.mitienda.view;

import desk.mitienda.controller.DatosEmpresaController;
import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.model.Rol;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;
import desk.mitienda.utils.Utilidades;

import java.awt.EventQueue;
import java.awt.BasicStroke;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroTiendaFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_correo;
	private JTextField txt_telefono;
	private JTextField txt_ruc;
	private JTextField txt_direccion;
	private JTextField txt_nombre_tienda;
	private JTextField txt_nombre_dueno;
	private DatosEmpresaController datosEmpresaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegistroTiendaFrame frame = new RegistroTiendaFrame();
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
	public RegistroTiendaFrame() {
		FlyWay.migrate();
		datosEmpresaController = new DatosEmpresaController();

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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				registrar();

			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Jockey One", Font.PLAIN, 23));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(256, 524, 181, 35);
		panel_11.add(btnNewButton);


		JLabel lblTelefono = new JLabel("Telefono ");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblTelefono.setBounds(10, 354, 357, 47);
		panel_11.add(lblTelefono);

		JLabel lblRucPropietario = new JLabel("RUC propietario");
		lblRucPropietario.setForeground(Color.WHITE);
		lblRucPropietario.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblRucPropietario.setBounds(10, 297, 357, 47);
		panel_11.add(lblRucPropietario);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblCorreo.setBounds(10, 411, 357, 47);
		panel_11.add(lblCorreo);







		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setForeground(Color.WHITE);
		lblDireccin.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblDireccin.setBounds(10, 240, 357, 47);
		panel_11.add(lblDireccin);



		JLabel lblNombreDeLa = new JLabel("Nombre de la Tienda ");
		lblNombreDeLa.setForeground(Color.WHITE);
		lblNombreDeLa.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblNombreDeLa.setBorder(null);
		lblNombreDeLa.setBounds(10, 183, 189, 47);
		panel_11.add(lblNombreDeLa);

		JLabel lblNombreDeDueo = new JLabel("Nombre de Dueño");
		lblNombreDeDueo.setForeground(Color.WHITE);
		lblNombreDeDueo.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblNombreDeDueo.setBorder(null);
		lblNombreDeDueo.setBounds(10, 126, 189, 47);
		panel_11.add(lblNombreDeDueo);


		txt_correo = createTextField(10, 5);
		txt_correo.setBounds(291, 411, 357, 35);
		panel_11.add(txt_correo);


		txt_telefono = createTextField(10, 5);
		txt_telefono.setBounds(291, 354, 357, 35);
		panel_11.add(txt_telefono);

		txt_ruc = createTextField(10, 5);
		txt_ruc.setBounds(291, 297, 357, 35);
		panel_11.add(txt_ruc);


		txt_direccion = createTextField(10, 5);
		txt_direccion.setBounds(291, 240, 357, 35);
		panel_11.add(txt_direccion);
		txt_direccion.setText("xd");

		txt_nombre_tienda = createTextField(10, 5);
		txt_nombre_tienda.setBounds(291, 183, 357, 35);
		panel_11.add(txt_nombre_tienda);


		txt_nombre_dueno = createTextField(10, 5);
		txt_nombre_dueno.setBounds(291, 126, 357, 35);
		panel_11.add(txt_nombre_dueno);






		JLabel lblNewLabel = new JLabel("REGISTRO DE DATOS DE TIENDA ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Jockey One", Font.PLAIN, 26));
		lblNewLabel.setBounds(195, 21, 357, 75);
		panel_11.add(lblNewLabel);

		JLabel lblIniciarSesion = new JLabel("INICIAR SESION ");
		lblIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFrame adminFrame = new AdminFrame();
				adminFrame.setVisible(true);
				adminFrame.setLocationRelativeTo(null);

				// Cierra el frame al hacer clic en el label
				dispose();

			}
		});
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Jockey One", Font.PLAIN, 23));
		lblIniciarSesion.setBorder(null);
		lblIniciarSesion.setBounds(1081, 702, 189, 47);
		panel.add(lblIniciarSesion);
	}
//	public void registrar() {
//
//		DatosEmpresa datosEmpresa = llenarDatosEmpresa();
//
//
//		// Validaciones
////		if(datosEmpresa.getNombreEmpresa().equals("")) {
////			JOptionPane.showMessageDialog(null, "El campo nombre no puede ir vacio");
////			return;
////		}
////		if(datosEmpresa.getNombres().equals("")) {
//
//		// Registrar
//		Estado estado = datosEmpresaController.guardar(datosEmpresa);
//		if(estado.getExito()) {
//
//
//			JOptionPane.showMessageDialog(null, estado.getMensaje());
//			RegistroTiendaFrame registroTiendaFrame = new RegistroTiendaFrame();
//			registroTiendaFrame.setLocationRelativeTo(null);
//			registroTiendaFrame.setVisible(true);
//			dispose();
//
//
//
//
//		} else {
//			JOptionPane.showMessageDialog(null, estado.getMensaje());
//		}
//	}
//	public DatosEmpresa llenarDatosEmpresa() {
//
//		return new DatosEmpresa(
//				null,
//				txt_nombre_tienda.getText(),
//				txt_nombre_dueno.getText(),
//				txt_direccion.getText(),
//				txt_telefono.getText(),
//				txt_correo.getText(),
//				true,
//				txt_ruc.getText()
//		);
//
//	}

}


