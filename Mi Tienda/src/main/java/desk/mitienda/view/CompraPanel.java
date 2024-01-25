package desk.mitienda.view;

import desk.mitienda.controller.CompraController;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Proveedor;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class CompraPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private CompraController compraController;
	private JTable table;
	private JTextField textNumero;
	private JTextField textEmpresa;
	private Long compraId;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnRefrescar;
	private JButton btnImprimir;
	
	public void listar(String numero, String empresa) {
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<Compra> listaCompras = compraController.listar(numero, empresa);
		modelo.addColumn("Id");
		modelo.addColumn("Numero");
		modelo.addColumn("Fecha");
		modelo.addColumn("Forma de pago");
		modelo.addColumn("Proveedor");
		modelo.addColumn("Subtotal");
		modelo.addColumn("Iva");
		modelo.addColumn("Total");

		listaCompras.forEach(compra -> modelo.addRow(new Object[]{
				compra.getId(),
				compra.getNumero(),
				compra.getFecha(),
				compra.getFormaPago(),
				compra.getProveedor().getEmpresa(),
				compra.getSubtotal(),
				compra.getIva(),
				compra.getTotal()
		}));

	}

	public void activarBotones() {
		btnModificar.setEnabled(true);
		btnImprimir.setEnabled(true);
	}
	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}
	public CompraPanel(int panelAncho, int panelAlto) {
		// Controllers
		compraController = new CompraController();

		setBackground(new Color(49, 51, 56));
		setBounds(0, 0, 1080, 800);
		setPreferredSize (new Dimension(1045, 677));
		setLayout(null);

		JLabel lblFactura = new JLabel("COMPRA");
		lblFactura.setForeground(new Color(255, 255, 255));
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Jockey One", Font.BOLD, 27));
		lblFactura.setBounds(0, 0, 1060, 46);
		add(lblFactura);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setFocusPainted(false);
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(Color.BLACK);
		btnNuevo.setBounds(33, 103, 100, 30);
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CompraFrame();
			}
		});
		add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setFocusPainted(false);
		btnModificar.setEnabled(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(Color.BLACK);
		btnModificar.setBounds(143, 103, 100, 30);
		add(btnModificar);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setBorder(null);
		btnRefrescar.setBorder(null);
		btnRefrescar.setBackground(Color.BLACK);
		btnRefrescar.setBounds(253, 103, 100, 30);
		btnRefrescar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listar(null, null);
			}
		});
		add(btnRefrescar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 144, 1000, 547);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				compraId = (Long) table.getValueAt(table.getSelectedRow(), 0);
				activarBotones();
			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
//		btnImprimir = new JButton("Imprimir");
//		btnImprimir.setForeground(Color.WHITE);
//		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
//		btnImprimir.setFocusPainted(false);
//		btnImprimir.setBorder(null);
//		btnImprimir.setBackground(Color.BLACK);
//		btnImprimir.setBounds(357, 103, 100, 30);
//		add(btnImprimir);
		
		JLabel lblBuscarPorNumero = new JLabel("Buscar por numero");
		lblBuscarPorNumero.setForeground(Color.WHITE);
		lblBuscarPorNumero.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorNumero.setBorder(null);
		lblBuscarPorNumero.setBounds(679, 70, 155, 38);
		add(lblBuscarPorNumero);
		
		textNumero = new JTextField();
		textNumero.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listar(textNumero.getText(), null);
			}
		});
		textNumero.setColumns(10);
		textNumero.setBounds(679, 105, 169, 28);
		add(textNumero);
		
		JLabel lblBuscarPorEmpresa = new JLabel("Buscar por empresa");
		lblBuscarPorEmpresa.setForeground(Color.WHITE);
		lblBuscarPorEmpresa.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorEmpresa.setBorder(null);
		lblBuscarPorEmpresa.setBounds(859, 70, 155, 38);
		add(lblBuscarPorEmpresa);
		
		textEmpresa = new JTextField();
		textEmpresa.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listar(null, textEmpresa.getText());
			}
		});
		textEmpresa.setColumns(10);
		textEmpresa.setBounds(858, 103, 169, 28);
		add(textEmpresa);

		listar(null, null);
	}
}
