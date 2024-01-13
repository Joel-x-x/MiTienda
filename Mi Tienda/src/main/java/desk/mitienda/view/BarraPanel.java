package desk.mitienda.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BarraPanel extends JPanel {
	private int administrador_id;
	private static final long serialVersionUID = -5747117121149449033L;
	private AdminFrame adminFrame;
	private JButton inicioButton;
    private JButton usuariosButton;
    private JButton tiposMembresiasButton;
    private JButton EntrenadorClasesButton;
    private JButton adminButton;
    private JLabel labelNombreGimnasio;
    int panelAncho = 1080, panelAlto = 750;
    private JButton facturaButton;
    private JButton membresiasButton;

    public BarraPanel(AdminFrame frame) {
        adminFrame = frame;

        setPreferredSize(new Dimension(200, 750));
        setBackground(new Color(46, 56, 64));
        
        inicioButton = new JButton("     Registro");
        inicioButton.setHorizontalAlignment(SwingConstants.LEFT);
        inicioButton.setFocusPainted(false);
        inicioButton.setFocusTraversalKeysEnabled(false);
        inicioButton.setBackground(new Color(46, 56, 64));
        inicioButton.setForeground(new Color(163, 175, 175));
        inicioButton.setBorder(null);
        inicioButton.setFont(new Font("Candara", Font.PLAIN, 18));
        inicioButton.setBounds(0, 197, 200, 40);
        usuariosButton = new JButton("     Usuarios");
        usuariosButton.setHorizontalAlignment(SwingConstants.LEFT);
        usuariosButton.setFocusPainted(false);
        usuariosButton.setFocusTraversalKeysEnabled(false);
        usuariosButton.setBackground(new Color(46, 56, 64));
        usuariosButton.setForeground(new Color(163, 175, 175));
        usuariosButton.setBorder(null);
        usuariosButton.setFont(new Font("Candara", Font.PLAIN, 18));
        usuariosButton.setBounds(0, 248, 200, 40);
        tiposMembresiasButton = new JButton("     Tipos de Membresías");
        tiposMembresiasButton.setHorizontalAlignment(SwingConstants.LEFT);
        tiposMembresiasButton.setFocusPainted(false);
        tiposMembresiasButton.setFocusTraversalKeysEnabled(false);
        tiposMembresiasButton.setBackground(new Color(46, 56, 64));
        tiposMembresiasButton.setForeground(new Color(163, 175, 175));
        tiposMembresiasButton.setBorder(null);
        tiposMembresiasButton.setFont(new Font("Candara", Font.PLAIN, 18));
        tiposMembresiasButton.setBounds(0, 299, 200, 40);
        
        EntrenadorClasesButton = new JButton("     Entrenadores y Clases");
        EntrenadorClasesButton.setHorizontalAlignment(SwingConstants.LEFT);
        EntrenadorClasesButton.setFocusPainted(false);
        EntrenadorClasesButton.setFocusTraversalKeysEnabled(false);
        EntrenadorClasesButton.setBackground(new Color(46, 56, 64));
        EntrenadorClasesButton.setForeground(new Color(163, 175, 175));
        EntrenadorClasesButton.setBorder(null);
        EntrenadorClasesButton.setFont(new Font("Candara", Font.PLAIN, 18));
        EntrenadorClasesButton.setBounds(0, 350, 200, 40);
        
        membresiasButton = new JButton("     Membresías");
        membresiasButton.setHorizontalAlignment(SwingConstants.LEFT);
        membresiasButton.setForeground(new Color(163, 175, 175));
        membresiasButton.setFont(new Font("Candara", Font.PLAIN, 18));
        membresiasButton.setFocusTraversalKeysEnabled(false);
        membresiasButton.setFocusPainted(false);
        membresiasButton.setBorder(null);
        membresiasButton.setBackground(new Color(46, 56, 64));
        membresiasButton.setBounds(0, 400, 200, 40);
        add(membresiasButton);
        
        facturaButton = new JButton("     Factura");
        facturaButton.setHorizontalAlignment(SwingConstants.LEFT);
        facturaButton.setForeground(new Color(163, 175, 175));
        facturaButton.setFont(new Font("Candara", Font.PLAIN, 18));
        facturaButton.setFocusTraversalKeysEnabled(false);
        facturaButton.setFocusPainted(false);
        facturaButton.setBorder(null);
        facturaButton.setBackground(new Color(46, 56, 64));
        facturaButton.setBounds(0, 450, 200, 40);
        add(facturaButton);
        
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.cambiarPanel(new UsuarioPanel(panelAncho, panelAlto));
            }
        });

        setLayout(null);
        add(inicioButton);
        add(usuariosButton);
        add(tiposMembresiasButton);
        add(EntrenadorClasesButton);

//        CircularPanel logoEmpresa = new CircularPanel();
//        add(logoEmpresa); 
        
//        JButton btnCerrarSesion = new JButton("     Cerrar Sesión");
//        btnCerrarSesion.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//
//					// Abre la ventana del panel de administrador
//	        		RegistroFrame registroFrame = new RegistroFrame();
//	        		registroFrame.setVisible(true);
//
//	        		// Cierra el frame de registro
//	        		adminFrame.dispose();
//	        		new ConnectionFactory();
//					ConnectionFactory.desconectar();
//        	}
//        });
//        btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
//        btnCerrarSesion.setForeground(new Color(163, 175, 175));
//        btnCerrarSesion.setFont(new Font("Candara", Font.PLAIN, 18));
//        btnCerrarSesion.setFocusTraversalKeysEnabled(false);
//        btnCerrarSesion.setFocusPainted(false);
//        btnCerrarSesion.setBorder(null);
//        btnCerrarSesion.setBackground(new Color(46, 56, 64));
//        btnCerrarSesion.setBounds(0, 646, 200, 40);
//        add(btnCerrarSesion);
        
        labelNombreGimnasio = new JLabel();
        
//        agregarNombreGym();
        
        labelNombreGimnasio.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombreGimnasio.setForeground(new Color(163, 175, 175));
        labelNombreGimnasio.setFont(new Font("Candara", Font.BOLD, 18));
        labelNombreGimnasio.setBounds(0, 137, 200, 22);
        add(labelNombreGimnasio);

    }
    
//    public void agregarNombreGym() {
//        String nombreGimnasio = cuentaController.getNombreEmpresa(administrador_id);
//        labelNombreGimnasio.setText(nombreGimnasio);
//    }

}