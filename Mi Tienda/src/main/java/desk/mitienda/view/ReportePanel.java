package desk.mitienda.view;

import desk.mitienda.controller.CompraController;
import desk.mitienda.controller.KardexController;
import desk.mitienda.controller.NotaVentaController;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Kardex;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import com.toedter.calendar.JDateChooser;
import desk.mitienda.model.NotaVenta;
import desk.mitienda.service.ReportesService;

public class ReportePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnNuevo;
	private JDateChooser fechaInicio;
	private JDateChooser fechaFin;
	private NotaVentaController notaVentaController;
	private CompraController compraController;
	private ReportesService reportesService;
	private KardexController kardexController;
	
	public Boolean validarCampos() {
		if(fechaInicio.getCalendar() == null) {
			JOptionPane.showMessageDialog(null, "El campo fecha inicio no puede ir vacio");
			return true;
		}

		if(fechaFin.getCalendar() == null) {
			JOptionPane.showMessageDialog(null, "El campo fecha fin no puede ir vacio");
			return true;
		}

		return false;
	}
	
	public void generarReporteVentas() {
		if(validarCampos()) {
			return;
		}

		fechaFin.getCalendar().add(Calendar.DAY_OF_MONTH, 1);

		LocalDate fechaInicioLocalDate = fechaInicio.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaFinLocalDate = fechaFin.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		List<NotaVenta> lista = notaVentaController.listarFiltroFechas(fechaInicioLocalDate, fechaFinLocalDate);

		reportesService.generarReporteVentas(lista);
	}

	public void generarReporteCompras() {
		if(validarCampos()) {
			return;
		}

		fechaFin.getCalendar().add(Calendar.DAY_OF_MONTH, 1);

		LocalDate fechaInicioLocalDate = fechaInicio.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaFinLocalDate = fechaFin.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		List<Compra> lista = compraController.listarFiltroFechas(fechaInicioLocalDate, fechaFinLocalDate);

		reportesService.generarReporteCompras(lista);
	}

	public void generarReporteKardex() {
		if(validarCampos()) {
			return;
		}

		fechaFin.getCalendar().add(Calendar.DAY_OF_MONTH, 1);

		LocalDate fechaInicioLocalDate = fechaInicio.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaFinLocalDate = fechaFin.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		List<Kardex> lista = kardexController.listarFiltroFechas(fechaInicioLocalDate, fechaFinLocalDate);

		reportesService.generarReporteKardex(lista);
	}

	public ReportePanel(int panelAncho, int panelAlto) {

		// Controllers
		notaVentaController = new NotaVentaController();
		reportesService = new ReportesService();
		compraController = new CompraController();
		kardexController = new KardexController();

		setBounds(ALLBITS, ABORT, 1080, 800);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(49, 51, 56));
		panel.setBounds(0, 0, 1080, 800);
		add(panel);
		panel.setLayout(null);
		//setPreferredSize (new Dimension(panelAncho, panelAlto));

		JLabel lblFactura = new JLabel("Reportes");
		lblFactura.setForeground(new Color(255, 255, 255));
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Jockey One", Font.BOLD, 27));
		lblFactura.setBounds(0, 0, 1060, 46);
		panel.add(lblFactura);

		btnNuevo = new JButton("Ventas");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNuevo.setFocusPainted(false);
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(new Color(128, 128, 128));
		btnNuevo.setBounds(27, 233, 274, 128);
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generarReporteVentas();
			}
		});
		panel.add(btnNuevo);

		JLabel lblBuscarPorIdentificacion = new JLabel("Filtros");
		lblBuscarPorIdentificacion.setForeground(Color.WHITE);
		lblBuscarPorIdentificacion.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblBuscarPorIdentificacion.setBorder(null);
		lblBuscarPorIdentificacion.setBounds(27, 67, 155, 38);
		panel.add(lblBuscarPorIdentificacion);
		
		fechaInicio = new JDateChooser();
		fechaInicio.setBounds(118, 116, 127, 25);
		panel.add(fechaInicio);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setForeground(Color.WHITE);
		lblFechaInicio.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblFechaInicio.setBorder(null);
		lblFechaInicio.setBounds(27, 107, 81, 38);
		panel.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setForeground(Color.WHITE);
		lblFechaFin.setFont(new Font("Jockey One", Font.PLAIN, 14));
		lblFechaFin.setBorder(null);
		lblFechaFin.setBounds(266, 107, 81, 38);
		panel.add(lblFechaFin);
		
		fechaFin = new JDateChooser();
		fechaFin.setBounds(346, 116, 127, 25);
		panel.add(fechaFin);
		
		JButton btnCompras = new JButton("Compras");
		btnCompras.setForeground(Color.WHITE);
		btnCompras.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCompras.setFocusPainted(false);
		btnCompras.setBorder(null);
		btnCompras.setBackground(Color.GRAY);
		btnCompras.setBounds(330, 233, 274, 128);
		btnCompras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generarReporteCompras();
			}
		});
		panel.add(btnCompras);

		JButton btnKardex = new JButton("Kardex");
		btnKardex.setForeground(Color.WHITE);
		btnKardex.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnKardex.setFocusPainted(false);
		btnKardex.setBorder(null);
		btnKardex.setBackground(Color.GRAY);
		btnKardex.setBounds(630, 233, 274, 128);
		btnKardex.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generarReporteKardex();
			}
		});
		panel.add(btnKardex);

	}
}
