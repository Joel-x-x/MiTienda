package desk.mitienda.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.FacturaController;
import com.gym.controller.MembresiaController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Factura;
import com.gym.model.Membresia;
import com.gym.utilidades.Utilidades;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;


public class FacturaPanel extends JPanel implements GenerarFacturaFrameInterfaz{
	private int administrador_id;
	private int idSeleccionado;
	private String numero_factura;
	private String cedula;
	private Double total;
	private FacturaController facturaController;
	private UsuarioController usuarioController;
	private DefaultTableModel modelo;
	private MembresiaController membresiaController;
	
	private static final long serialVersionUID = 1L;
	private JTextField textBuscar;
	private JTable table;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnRefrescar;
	private JButton btnNewButton_3;
	private JButton btnFormaPago;
	
	
	public void listar() {
		String[] cabecera = {"Id" , "No Factura", "Cliente", "Cedula", "Subtotal", "IVA", "Total", "Forma de pago", "Fecha", "Establecimiento", "Punto de Emisión"};
		
		modelo = new DefaultTableModel(facturaController.listarFactura(administrador_id, textBuscar.getText()), cabecera);
		
		table.setModel(modelo);
	}
	
	@Override
	public void llamarFrame(boolean nuevo) {
		new FacturaFrame(this, nuevo);
	}

	@Override
	public void accion(Factura factura) {
		JOptionPane.showMessageDialog(null, "Se guardo correctamente!");
		listar();
	}
	

	public void modificarFactura() {
		llamarFrame(false);
	}
	
	public void eliminarFactura() {
		
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar está factura");
		
		if(respuesta == 0) {
			JOptionPane.showMessageDialog(null, Utilidades.codigoToMensajeEliminar(facturaController.eliminarFactura(idSeleccionado),
					"No se pudo eliminar, parece que tienes registros que dependen de esta factura, "
							+ "solo puedes modificarla"));
			listar();
			bloquearBotones();
		}
		
	}
	
	public void generarPDF(String nombre) throws FileNotFoundException, DocumentException{
		FileOutputStream archivo = new FileOutputStream (nombre + ".pdf");
		Document documento = new Document();
		PdfWriter.getInstance(documento, archivo);
		documento.open();
		
		Paragraph cabeceraFactura = new Paragraph("Nº Factura"+ nombre );
		cabeceraFactura.setAlignment(1);
		documento.add(cabeceraFactura);
		documento.add(new Paragraph("Cliente: " ));
		
	}
	
	public void listarIvas() {
		List<Factura> listaIvas = facturaController.listarIvas(administrador_id);
		
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Factura item : listaIvas) {
            listModel.addElement(item.getIvaPorcentaje() + "     " + item.getFechaIva());
        }
        
        new IvaFrame(listModel);
	}
	
	public void formaPago() {
		//new FormaPagoFrame(numero_factura, cedula, total, idSeleccionado, usuarioController.consultarUsuarioId(cedula));
	}
	
	public void bloquearBotones() {
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnFormaPago.setEnabled(false);
	}
	
	public void activarBotones() {
		btnModificar.setEnabled(true);
		btnEliminar.setEnabled(true);
		btnFormaPago.setEnabled(true);
	}
	
	@Override
	public int getIdSeleccionado() {
		return idSeleccionado;
	}
	
	@Override
	public void setIdSeleccionado(int idSeleccionado) {
		this.idSeleccionado = idSeleccionado;
	}
	
	public void generarPDF() {
		
		try {
			Factura factura = facturaController.consultarFactura(idSeleccionado);
			List<Membresia> membresias =  membresiaController.listarMembresiaFacturaList(administrador_id, factura.getId());
			String ruta = System.getProperty("user.home");
			FileOutputStream archivo = new FileOutputStream(ruta + "/Downloads/" +factura.getNumero_factura()+".pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			
			Paragraph parrafo = new Paragraph("NÚMERO DE FACTURA: " + factura.getNumero_factura());
			parrafo.setAlignment(1);
			documento.add(parrafo);
			
			
			documento.add(new Paragraph("   CLIENTE: " + factura.getNombreUsuario()));
			documento.add(new Paragraph("   CEDULA: " + factura.getCedulaUsuario()));
			documento.add(new Paragraph("   ESTABLECIMIENTO: " + factura.getEstablecimiento()));
			documento.add(new Paragraph("   FECHA DE EMISION: "+ factura.getFecha()));
			documento.add(new Paragraph("   FORMA DE PAGO: " + factura.getForma_pago()));
			documento.add(new Paragraph("------------------------------------------------------------------"));
			documento.add(new Paragraph("	CANT.DESCRIPCIÓN"+"                             "+"P.TOTAL")); //8-tabs
			documento.add(new Paragraph("------------------------------------------------------------------"));
			
			membresias.forEach(x-> {
				try {
					documento.add(new Paragraph(x.getNombreTipo()));
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			documento.add(new Paragraph("------------------------------------------------------------------"));
			documento.add(new Paragraph("      SUBTOTAL:"+"                                "+ factura.getSubtotal()));
			documento.add(new Paragraph("   DESCUENTO %:"+"                                "+ factura.getDescuento()));
			documento.add(new Paragraph("       IVA 12%:"+"                                "+ factura.getIva()));
			documento.add(new Paragraph("   VALOR TOTAL:"+"                                "+ factura.getTotal()));
			documento.add(new Paragraph("------------------------------------------------------------------"));
			documento.close();
			
		} catch ( FileNotFoundException e1) {
			
			System.out.println(e1);
		} catch (DocumentException e1) {
			
			e1.printStackTrace();
		}
		
    	String ruta = System.getProperty("user.home");
		File path = new File(ruta +"/Downloads/"+table.getValueAt(table.getSelectedRow(), 1) + ".pdf");
		System.out.println(path);
		
		if (path.exists()) {
            try {
                Desktop.getDesktop().open(path);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
		}
    }

	public FacturaPanel(int panelAncho, int panelAlto) {
		setPreferredSize (new Dimension(panelAlto, panelAlto));
		administrador_id = new Administrador().getId();
		facturaController = new FacturaController();
		usuarioController = new UsuarioController();
		membresiaController = new MembresiaController();
		
		setLayout(null);
		setFocusTraversalPolicyProvider(true);
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarFrame(true);
			}
		});
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setFocusPainted(false);
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(new Color(46, 56, 64));
		btnNuevo.setBounds(40, 98, 100, 30);
		add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarFactura();
			}
		});
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setFocusPainted(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(46, 56, 64));
		btnModificar.setBounds(150, 98, 100, 30);
		add(btnModificar);
		
		btnEliminar = new JButton("Anular");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarFactura();
			}
		});
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(46, 56, 64));
		btnEliminar.setBounds(260, 98, 100, 30);
		add(btnEliminar);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBuscar.setText("");
				listar();
			}
		});
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setBorder(null);
		btnRefrescar.setBackground(new Color(46, 56, 64));
		btnRefrescar.setBounds(370, 98, 100, 30);
		add(btnRefrescar);
		
		JLabel lblFactura = new JLabel("FACTURA");
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(10, 11, 1060, 46);
		add(lblFactura);
		
		JLabel lblNewLabel_2_1 = new JLabel("Buscar Nombre/Cedula:");
		lblNewLabel_2_1.setBounds(763, 102, 160, 22);
		add(lblNewLabel_2_1);
		
		textBuscar = new JTextField();
		textBuscar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listar();
			}
		});
		textBuscar.setBounds(900, 101, 140, 25);
		add(textBuscar);
		textBuscar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 139, 1000, 547);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
				activarBotones();
			}
		});
		
		
		scrollPane.setViewportView(table);
		//setPreferredSize(new Dimension(1080, 800));
        setBackground(Color.WHITE);

        JButton btnNewButton_2 = new JButton("Imprimir");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		generarPDF();
        	}
        	
        });
        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setBorder(null);
        btnNewButton_2.setBackground(new Color(46, 56, 64));
        btnNewButton_2.setBounds(480, 98, 100, 30);
        add(btnNewButton_2);
        
        btnNewButton_3 = new JButton("Actualizar IVA");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		listarIvas();
        	}
        });
        btnNewButton_3.setForeground(Color.WHITE);
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_3.setFocusPainted(false);
        btnNewButton_3.setBorder(null);
        btnNewButton_3.setBackground(new Color(46, 56, 64));
        btnNewButton_3.setBounds(940, 60, 100, 30);
        add(btnNewButton_3);
        
        btnFormaPago = new JButton("Forma de pago");
        btnFormaPago.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		numero_factura = (String) table.getValueAt(table.getSelectedRow(), 1);
        		cedula = (String) table.getValueAt(table.getSelectedRow(), 3);
        		total = (Double) table.getValueAt(table.getSelectedRow(), 6);
        		formaPago();
        	}
        });
        btnFormaPago.setForeground(Color.WHITE);
        btnFormaPago.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnFormaPago.setFocusPainted(false);
        btnFormaPago.setBorder(null);
        btnFormaPago.setBackground(new Color(46, 56, 64));
        btnFormaPago.setBounds(590, 98, 100, 30);
        add(btnFormaPago);
        
        listar();
        bloquearBotones();
	}
}
