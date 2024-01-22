package desk.mitienda.view;

import desk.mitienda.controller.IvaController;
import desk.mitienda.controller.NotaVentaController;
import desk.mitienda.model.NotaVenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class NotaVentaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private IvaController ivaController;
	private JTable table;
	private JTextField textNumero;
	private JTextField textIdentificacion;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnRefrescar;
	private JButton btnImprimir;
	private DefaultTableModel modelo;
	private NotaVentaController notaVentaController;

	public void listar(String numero, String identificacion) {
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<NotaVenta> listaNotas = notaVentaController.listar(numero, identificacion);
		modelo.addColumn("Id");
		modelo.addColumn("Cliente");
		modelo.addColumn("Cedula");
		modelo.addColumn("Numero");
		modelo.addColumn("Fecha");
		modelo.addColumn("Forma de pago");
		modelo.addColumn("Total");

		listaNotas.forEach(nota -> modelo.addRow(new Object[]{
				nota.getId(),
				nota.getCliente().getNombre() + " " + nota.getCliente().getApellido(),
				nota.getCliente().getIdentificacion(),
				nota.getNumero(),
				nota.getFecha(),
				nota.getFormaPago(),
				nota.getTotal()
		}));

	}
	
	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}
	
	public NotaVentaPanel(int panelAncho, int panelAlto) {

		// Controllers
		notaVentaController = new NotaVentaController();
		ivaController = new IvaController();
		setBounds(ALLBITS, ABORT, 1080, 800);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1080, 800);
		add(panel);
		panel.setLayout(null);
		//setPreferredSize (new Dimension(panelAncho, panelAlto));
		
		JLabel lblFactura = new JLabel("Nota de Venta");
		lblFactura.setForeground(new Color(255, 255, 255));
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Jockey One", Font.BOLD, 27));
		lblFactura.setBounds(0, 0, 1060, 46);
		panel.add(lblFactura);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setFocusPainted(false);
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(Color.BLACK);
		btnNuevo.setBounds(27, 103, 100, 30);
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NotaVentaFrame();
			}
		});
		panel.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setFocusPainted(false);
		btnModificar.setEnabled(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(Color.BLACK);
		btnModificar.setBounds(137, 103, 100, 30);
		panel.add(btnModificar);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setBorder(null);
		btnRefrescar.setBackground(Color.BLACK);
		btnRefrescar.setBounds(247, 103, 100, 30);
		btnRefrescar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listar(null, null);
			}
		});
		panel.add(btnRefrescar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 144, 1000, 547);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setFocusPainted(false);
		btnImprimir.setBorder(null);
		btnImprimir.setBackground(Color.BLACK);
		btnImprimir.setBounds(357, 103, 100, 30);
		panel.add(btnImprimir);
		
		JButton btnNewButton_3 = new JButton("Actualizar IVA");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(new Color(0, 0, 0));
		btnNewButton_3.setBounds(27, 62, 100, 30);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IvaFrame();
			}
		});
		panel.add(btnNewButton_3);
		
		JLabel lblBuscarPorNumero = new JLabel("Buscar por numero");
		lblBuscarPorNumero.setForeground(Color.WHITE);
		lblBuscarPorNumero.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorNumero.setBorder(null);
		lblBuscarPorNumero.setBounds(710, 70, 155, 38);
		panel.add(lblBuscarPorNumero);
		
		textNumero = new JTextField();
		textNumero.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listar(textNumero.getText(), null);
			}
		});
		textNumero.setColumns(10);
		textNumero.setBounds(710, 104, 146, 28);
		panel.add(textNumero);
		
		JLabel lblBuscarPorIdentificacion = new JLabel("Buscar por identificacion");
		lblBuscarPorIdentificacion.setForeground(Color.WHITE);
		lblBuscarPorIdentificacion.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorIdentificacion.setBorder(null);
		lblBuscarPorIdentificacion.setBounds(889, 70, 155, 38);
		panel.add(lblBuscarPorIdentificacion);
		
		textIdentificacion = new JTextField();
		textIdentificacion.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listar(null, textIdentificacion.getText());
			}
		});
		textIdentificacion.setColumns(10);
		textIdentificacion.setBounds(889, 104, 138, 28);
		panel.add(textIdentificacion);
		
		JButton btnCerrarCaja = new JButton("Cerrar caja");
		btnCerrarCaja.setForeground(Color.WHITE);
		btnCerrarCaja.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarCaja.setFocusPainted(false);
		btnCerrarCaja.setBorder(null);
		btnCerrarCaja.setBackground(Color.BLACK);
		btnCerrarCaja.setBounds(577, 103, 100, 30);
		panel.add(btnCerrarCaja);
		
		JButton btnAbrirCaja = new JButton("Abrir caja");
		btnAbrirCaja.setForeground(Color.WHITE);
		btnAbrirCaja.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAbrirCaja.setFocusPainted(false);
		btnAbrirCaja.setBorder(null);
		btnAbrirCaja.setBackground(Color.BLACK);
		btnAbrirCaja.setBounds(467, 103, 100, 30);
		panel.add(btnAbrirCaja);

		// Listar
		listar(null, null);
	}
}
