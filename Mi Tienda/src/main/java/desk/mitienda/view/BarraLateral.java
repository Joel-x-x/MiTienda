package desk.mitienda.view;

import desk.mitienda.controller.DatosEmpresaController;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.model.Rol;
import desk.mitienda.utils.Utilidades;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class BarraLateral extends JPanel {

	private static final long serialVersionUID = 1L;
	private AdminFrame adminFrame;
	int panelAncho = 1080, panelAlto = 800;
	private JButton btnVenta;
	private JButton btnProductos;
	private JButton btnClientes;
	private JButton btnReportes;
	private JButton btnCaja;
	private JButton btnUsuario;
	private JButton btnCompra;
	private JButton btnProveedores;
	private JLabel labelTienda;
	private DatosEmpresaController datosEmpresaController;

	/**
	 * Create the panel.
	 */
	public BarraLateral(AdminFrame frame) {
		adminFrame = frame;
		datosEmpresaController = new DatosEmpresaController();
		setBackground(new Color(43, 45, 49));
		setPreferredSize(new Dimension(200, 800));
		setLayout(null);
		
		btnVenta = new JButton("VENTA ");
		btnVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new NotaVentaPanel(panelAncho, panelAlto));
				
			}
		});
		btnVenta.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnVenta.setForeground(new Color(255, 255, 255));
		btnVenta.setBorder(null);
		btnVenta.setBackground(new Color(64, 66, 73));
		btnVenta.setBounds(10, 68, 180, 42);
		add(btnVenta);
		
		btnProductos = new JButton("PRODUCTOS");
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
		
		btnClientes = new JButton("CLIENTES");
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
		
		btnReportes = new JButton("REPORTES");
		btnReportes.setForeground(Color.WHITE);
		btnReportes.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnReportes.setBorder(null);
		btnReportes.setBackground(new Color(64, 66, 73));
		btnReportes.setBounds(10, 253, 180, 42);
		btnReportes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new ReportePanel(panelAncho, panelAlto));
			}
		});
		add(btnReportes);
		
		btnCaja = new JButton("CAJA");
		btnCaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new CajaPanel(panelAncho, panelAlto));
			}
		});
		btnCaja.setForeground(Color.WHITE);
		btnCaja.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnCaja.setBorder(null);
		btnCaja.setBackground(new Color(64, 66, 73));
		btnCaja.setBounds(10, 315, 180, 42);
		add(btnCaja);
		
		btnUsuario = new JButton("USUARIO");
		btnUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new UsuarioPanel(panelAncho, panelAlto));
			}
		});
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnUsuario.setBorder(null);
		btnUsuario.setBackground(new Color(64, 66, 73));
		btnUsuario.setBounds(10, 379, 180, 42);
		add(btnUsuario);
		
		btnCompra = new JButton("COMPRA");
		btnCompra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new CompraPanel(panelAncho, panelAlto));
				
			}
		});
		btnCompra.setForeground(Color.WHITE);
		btnCompra.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnCompra.setBorder(null);
		btnCompra.setBackground(new Color(64, 66, 73));
		btnCompra.setBounds(10, 438, 180, 42);
		add(btnCompra);
		
		btnProveedores = new JButton("PROVEEDORES");
		btnProveedores.setForeground(Color.WHITE);
		btnProveedores.setFont(new Font("Jockey One", Font.PLAIN, 14));
		btnProveedores.setBorder(null);
		btnProveedores.setBackground(new Color(64, 66, 73));
		btnProveedores.setBounds(10, 500, 180, 42);
		btnProveedores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminFrame.cambiarPanel(new ProveerdorPanel(panelAncho, panelAlto));
			}
		});
		add(btnProveedores);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(64, 66, 73));
		panel.setBounds(10, 675, 175, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel labelUsuario = new JLabel(Utilidades.getUsuario().getNombre() + " | " + Utilidades.getUsuario().getRol());
		labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuario.setForeground(Color.WHITE);
		labelUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		labelUsuario.setBorder(null);
		labelUsuario.setBackground(new Color(64, 66, 73));
		labelUsuario.setAlignmentY(1.0f);
		labelUsuario.setAlignmentX(1.0f);
		labelUsuario.setBounds(10, 11, 155, 28);
		panel.add(labelUsuario);
		
		labelTienda = new JLabel(datosEmpresaController.getDatosEmpresa().getNombreEmpresa());
		labelTienda.setHorizontalAlignment(SwingConstants.CENTER);
		labelTienda.setForeground(Color.WHITE);
		labelTienda.setFont(new Font("Jockey One", Font.PLAIN, 16));
		labelTienda.setBorder(null);
		labelTienda.setBackground(new Color(64, 66, 73));
		labelTienda.setAlignmentY(1.0f);
		labelTienda.setAlignmentX(1.0f);
		labelTienda.setBounds(10, 11, 180, 28);
		add(labelTienda);

		switch (Utilidades.getUsuario().getRol()) {
			case ADMINISTRADOR ->  {
				btnVenta.setEnabled(true);
				btnProductos.setEnabled(true);
				btnClientes.setEnabled(true);
				btnReportes.setEnabled(true);
				btnCaja.setEnabled(true);
				btnUsuario.setEnabled(true);
				btnCompra.setEnabled(true);
				btnProveedores.setEnabled(true);
			}
			case VENDEDOR -> {
				btnVenta.setEnabled(true);
				btnProductos.setEnabled(true);
				btnClientes.setEnabled(true);
				btnReportes.setEnabled(false);
				btnCaja.setEnabled(false);
				btnUsuario.setEnabled(false);
				btnCompra.setEnabled(false);
				btnProveedores.setEnabled(false);
			}
			case BODEGUERO -> {
				btnVenta.setEnabled(false);
				btnProductos.setEnabled(true);
				btnClientes.setEnabled(false);
				btnReportes.setEnabled(false);
				btnCaja.setEnabled(false);
				btnUsuario.setEnabled(false);
				btnCompra.setEnabled(false);
				btnProveedores.setEnabled(false);
			}
		}


	}
}
