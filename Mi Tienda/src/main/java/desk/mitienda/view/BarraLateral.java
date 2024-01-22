package desk.mitienda.view;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BarraLateral extends JPanel {

	private static final long serialVersionUID = 1L;
	private AdminFrame adminFrame;
	int panelAncho = 1080, panelAlto = 800;

	/**
	 * Create the panel.
	 */
	public BarraLateral(AdminFrame frame) {
		adminFrame = frame;
		setBackground(new Color(43, 45, 49));
		setPreferredSize(new Dimension(200, 800));
		setLayout(null);
		
		JButton btnNewButton = new JButton("VENTA ");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new NotaVentaPanel(panelAncho, panelAlto));
				
			}
		});
		btnNewButton.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(64, 66, 73));
		btnNewButton.setBounds(10, 68, 180, 42);
		add(btnNewButton);
		
		JButton btnProductos = new JButton("PRODUCTOS");
		btnProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 adminFrame.cambiarPanel(new ProductoPanel(panelAncho, panelAlto));
			}
		});
		btnProductos.setForeground(Color.WHITE);
		btnProductos.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnProductos.setBorder(null);
		btnProductos.setBackground(new Color(64, 66, 73));
		btnProductos.setBounds(10, 132, 180, 42);
		add(btnProductos);
		
		JButton btnClientes = new JButton("CLIENTES");
		btnClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new ClientePanel(panelAncho, panelAlto));
			}
		});
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnClientes.setBorder(null);
		btnClientes.setBackground(new Color(64, 66, 73));
		btnClientes.setBounds(10, 191, 180, 42);
		add(btnClientes);
		
		JButton btnReportes = new JButton("REPORTES");
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnReportes.setBorder(null);
		btnReportes.setBackground(new Color(64, 66, 73));
		btnReportes.setBounds(10, 253, 180, 42);
		add(btnReportes);
		
		JButton btnIva = new JButton("CAJA");
		btnIva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new CajaPanel(panelAncho, panelAlto));
			}
		});
		btnIva.setForeground(Color.WHITE);
		btnIva.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnIva.setBorder(null);
		btnIva.setBackground(new Color(64, 66, 73));
		btnIva.setBounds(10, 315, 180, 42);
		add(btnIva);
		
		JButton btnNewButton_1_1 = new JButton("USUARIO");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new UsuarioPanel(panelAncho, panelAlto));
			}
		});
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(new Color(64, 66, 73));
		btnNewButton_1_1.setBounds(10, 379, 180, 42);
		add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("COMPRA");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new CompraPanel(panelAncho, panelAlto));
				
			}
		});
		btnNewButton_2_1.setForeground(Color.WHITE);
		btnNewButton_2_1.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnNewButton_2_1.setBorder(null);
		btnNewButton_2_1.setBackground(new Color(64, 66, 73));
		btnNewButton_2_1.setBounds(10, 438, 180, 42);
		add(btnNewButton_2_1);
		
		JButton btnNewButton_3_1 = new JButton("PROVEEDORES");
		btnNewButton_3_1.setForeground(Color.WHITE);
		btnNewButton_3_1.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnNewButton_3_1.setBorder(null);
		btnNewButton_3_1.setBackground(new Color(64, 66, 73));
		btnNewButton_3_1.setBounds(10, 500, 180, 42);
		btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new ProveerdorPanel(panelAncho, panelAlto));
			}
		});
		add(btnNewButton_3_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 66, 73));
		panel.setBounds(10, 675, 175, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Jockey One", Font.PLAIN, 16));
		lblUsuario.setBorder(null);
		lblUsuario.setBackground(new Color(64, 66, 73));
		lblUsuario.setAlignmentY(1.0f);
		lblUsuario.setAlignmentX(1.0f);
		lblUsuario.setBounds(22, 27, 80, 13);
		panel.add(lblUsuario);

	}
}
