package desk.mitienda.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FacturaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	public FacturaFrame() {
		setBounds(new Rectangle(0, 0, 1080, 800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(43, 45, 49));
		panel.setBounds(0, 0, 1105, 867);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFactura = new JLabel("FACTURA");
		lblFactura.setForeground(Color.WHITE);
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(0, 0, 1064, 46);
		panel.add(lblFactura);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Cliente");
		lblNewLabel_1_4_2.setForeground(Color.WHITE);
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2.setBounds(10, 62, 50, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 64, 128));
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(59, 56, 150, 25);
		panel.add(textField);
		
		JButton btnBuscarCliente = new JButton("Buscar un cliente");
		btnBuscarCliente.setForeground(Color.WHITE);
		btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarCliente.setFocusPainted(false);
		btnBuscarCliente.setBorder(null);
		btnBuscarCliente.setBackground(Color.BLACK);
		btnBuscarCliente.setBounds(10, 99, 199, 30);
		panel.add(btnBuscarCliente);
		
		JLabel lblNewLabel_1_4_2_1 = new JLabel("Nombre:");
		lblNewLabel_1_4_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1.setBounds(10, 158, 50, 14);
		panel.add(lblNewLabel_1_4_2_1);
		
		JLabel labelCliente = new JLabel("");
		labelCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelCliente.setBounds(59, 140, 350, 46);
		panel.add(labelCliente);
		
		JButton btnBuscarMembresia = new JButton("Buscar membresías");
		btnBuscarMembresia.setForeground(Color.WHITE);
		btnBuscarMembresia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarMembresia.setFocusPainted(false);
		btnBuscarMembresia.setBorder(null);
		btnBuscarMembresia.setBackground(Color.BLACK);
		btnBuscarMembresia.setBounds(10, 197, 199, 25);
		panel.add(btnBuscarMembresia);
		
		JButton btnEliminarMembresia = new JButton("Eliminar");
		btnEliminarMembresia.setForeground(Color.WHITE);
		btnEliminarMembresia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminarMembresia.setFocusPainted(false);
		btnEliminarMembresia.setEnabled(false);
		btnEliminarMembresia.setBorder(null);
		btnEliminarMembresia.setBackground(Color.BLACK);
		btnEliminarMembresia.setBounds(219, 197, 100, 25);
		panel.add(btnEliminarMembresia);
		
		JLabel lblNewLabel_1_4_2_3 = new JLabel("No Factura");
		lblNewLabel_1_4_2_3.setForeground(Color.WHITE);
		lblNewLabel_1_4_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2_3.setBounds(658, 57, 77, 14);
		panel.add(lblNewLabel_1_4_2_3);
		
		JLabel labelEstablecimiento = new JLabel("");
		labelEstablecimiento.setForeground(Color.GRAY);
		labelEstablecimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelEstablecimiento.setBounds(729, 39, 70, 46);
		panel.add(labelEstablecimiento);
		
		JLabel labelNumeroFactura = new JLabel("000000001");
		labelNumeroFactura.setForeground(Color.WHITE);
		labelNumeroFactura.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelNumeroFactura.setBounds(814, 39, 250, 46);
		panel.add(labelNumeroFactura);
		
		JLabel Establecimiento = new JLabel("Establecimiento");
		Establecimiento.setForeground(Color.WHITE);
		Establecimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento.setBounds(658, 98, 99, 14);
		panel.add(Establecimiento);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setDragEnabled(true);
		textField_1.setColumns(10);
		textField_1.setBounds(751, 93, 250, 25);
		panel.add(textField_1);
		
		JLabel Establecimiento_1 = new JLabel("Forma de pago");
		Establecimiento_1.setForeground(Color.WHITE);
		Establecimiento_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1.setBounds(658, 140, 99, 14);
		panel.add(Establecimiento_1);
		
		JLabel Establecimiento_1_1 = new JLabel("Descuento %");
		Establecimiento_1_1.setForeground(Color.WHITE);
		Establecimiento_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1_1.setBounds(658, 180, 77, 14);
		panel.add(Establecimiento_1_1);
		
		textField_2 = new JTextField();
		textField_2.setText("0.0");
		textField_2.setDragEnabled(true);
		textField_2.setColumns(10);
		textField_2.setBounds(729, 175, 50, 25);
		panel.add(textField_2);
		
		JLabel Establecimiento_1_1_1 = new JLabel("Fecha");
		Establecimiento_1_1_1.setForeground(Color.WHITE);
		Establecimiento_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1_1_1.setBounds(799, 180, 50, 14);
		panel.add(Establecimiento_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 233, 1010, 248);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setForeground(Color.WHITE);
		lblDescuento.setBackground(Color.BLACK);
		lblDescuento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento.setBounds(785, 518, 129, 46);
		panel.add(lblDescuento);
		
		JComboBox<String> comboBoxFormaPago = new JComboBox<String>();
		comboBoxFormaPago.setBounds(750, 139, 250, 25);
		panel.add(comboBoxFormaPago);
		
		JLabel lblDescuento_1 = new JLabel("Subtotal");
		lblDescuento_1.setForeground(Color.WHITE);
		lblDescuento_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento_1.setBounds(785, 565, 129, 46);
		panel.add(lblDescuento_1);
		
		JLabel lblDescuento_2 = new JLabel("IVA");
		lblDescuento_2.setForeground(Color.WHITE);
		lblDescuento_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento_2.setBounds(785, 613, 129, 46);
		panel.add(lblDescuento_2);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(785, 660, 129, 46);
		panel.add(lblTotal);
		
		JLabel labelDescuento = new JLabel("0.0");
		labelDescuento.setForeground(Color.WHITE);
		labelDescuento.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelDescuento.setBounds(924, 518, 129, 46);
		panel.add(labelDescuento);
		
		JLabel labelSubtotal = new JLabel("0.0");
		labelSubtotal.setForeground(Color.WHITE);
		labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSubtotal.setBounds(924, 565, 129, 46);
		panel.add(labelSubtotal);
		
		JLabel labelIVA = new JLabel("0.0");
		labelIVA.setForeground(Color.WHITE);
		labelIVA.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelIVA.setBounds(924, 613, 129, 46);
		panel.add(labelIVA);
		
		JLabel labelTotal = new JLabel("0.0");
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTotal.setBounds(924, 660, 129, 46);
		panel.add(labelTotal);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(46, 56, 64));
		btnGuardar.setBounds(76, 674, 100, 30);
		panel.add(btnGuardar);
	}
}
