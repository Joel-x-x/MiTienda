package desk.mitienda.view;

import desk.mitienda.controller.CajaController;
import desk.mitienda.model.Caja;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class CajaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private CajaController cajaController;
	private DefaultTableModel modelo;
	private JTextField textBuscarNombre;

	private void listarCajas(String nombre){
		borrarDatosTabla();
		modelo = (DefaultTableModel) table.getModel();
		List<Caja> listaCajas = cajaController.listar(nombre);
		modelo.addColumn("Id");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Rol");
		modelo.addColumn("Apertura");
		modelo.addColumn("Monto Apertura");
		modelo.addColumn("Cierre");
		modelo.addColumn("Monto cierre");
		modelo.addColumn("Valor calculado");
		modelo.addColumn("Descuadre");

		listaCajas.forEach(caja -> modelo.addRow(new Object[]{
				caja.getId(),
				caja.getUsuario().getNombre(),
				caja.getUsuario().getApellido(),
				caja.getUsuario().getRol(),
				caja.getApertura(),
				caja.getMontoApertura(),
				caja.getCierre(),
				caja.getMontoCierre(),
				caja.getValorCalculado(),
				caja.getDescuadre()
		}));

	}

	private void borrarDatosTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
	}

	public CajaPanel(int panelAncho, int panelAlto) {
		cajaController = new CajaController();

		setBackground(new Color(49, 51, 56));
		setBounds(0, 0, 1080, 800);
		setLayout(null);
		setPreferredSize(new Dimension(1117, 660));
		
		JLabel lblHistorialDePrecios = new JLabel("Caja");
		lblHistorialDePrecios.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDePrecios.setForeground(Color.WHITE);
		lblHistorialDePrecios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHistorialDePrecios.setBounds(0, 0, 1030, 37);
		add(lblHistorialDePrecios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(20, 111, 1030, 515);
		add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblBuscarPorUsuario = new JLabel("Buscar por nombre:");
		lblBuscarPorUsuario.setForeground(Color.WHITE);
		lblBuscarPorUsuario.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorUsuario.setBorder(null);
		lblBuscarPorUsuario.setBounds(20, 59, 114, 38);
		add(lblBuscarPorUsuario);
		
		textBuscarNombre = new JTextField();
		textBuscarNombre.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listarCajas(textBuscarNombre.getText());
			}
		});
		textBuscarNombre.setColumns(10);
		textBuscarNombre.setBounds(147, 66, 169, 28);
		add(textBuscarNombre);

		listarCajas(null);
	}
}
