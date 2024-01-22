package desk.mitienda.view;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import desk.mitienda.controller.CategoriaController;
import desk.mitienda.model.Categoria;
import desk.mitienda.utils.Estado;

import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoriaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCategoria;
	private CategoriaController categoriaController;
	private JList<String> list;
	private Long categoriaId;

	public void listar() {
		List<Categoria> categorias = categoriaController.listar();

		DefaultListModel<String> listModel = new DefaultListModel<>();

		for (Categoria categoria : categorias) {
			listModel.addElement(categoria.getId() + "  ->  " + categoria.getCategoria());
		}
		list.setModel(listModel);
	}

	public void agregar() {
		Categoria categoria = Categoria.builder().categoria(textCategoria.getText()).build();


		Estado estado = categoriaController.guardar(categoria);

		JOptionPane.showMessageDialog(null, estado.getMensaje());

		listar();
	}

	public void actualizar() {
		Categoria categoria = Categoria.builder().categoria(textCategoria.getText()).build();
		categoria.setId(categoriaId);

		Estado estado = categoriaController.actualizar(categoria);

		JOptionPane.showMessageDialog(null, estado.getMensaje());

		listar();
	}

	/**
	 * Create the frame.
	 */
	public CategoriaFrame() {
		// Controllers
		categoriaController = new CategoriaController();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 638, 694);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(43, 45, 49));
		panel.setBounds(0, 0, 1105, 867);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFactura = new JLabel("Categorias");
		lblFactura.setForeground(Color.WHITE);
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(0, 0, 622, 46);
		panel.add(lblFactura);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Categoria");
		lblNewLabel_1_4_2.setForeground(Color.WHITE);
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2.setBounds(10, 62, 78, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textCategoria = new JTextField();
		textCategoria.setForeground(new Color(0, 64, 128));
		textCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
		textCategoria.setColumns(10);
		textCategoria.setBounds(87, 57, 150, 25);
		panel.add(textCategoria);
		
		JLabel labelEstablecimiento = new JLabel("");
		labelEstablecimiento.setForeground(Color.GRAY);
		labelEstablecimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelEstablecimiento.setBounds(729, 39, 70, 46);
		panel.add(labelEstablecimiento);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(785, 660, 129, 46);
		panel.add(lblTotal);
		
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
		
		JButton btn_agregar_usuario = new JButton("Agregar");
		btn_agregar_usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		btn_agregar_usuario.setForeground(Color.WHITE);
		btn_agregar_usuario.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_agregar_usuario.setBorder(null);
		btn_agregar_usuario.setBackground(Color.BLACK);
		btn_agregar_usuario.setBounds(10, 93, 100, 28);
		panel.add(btn_agregar_usuario);
		
		JButton btn_modificar = new JButton("Modificar");
		btn_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		btn_modificar.setForeground(Color.WHITE);
		btn_modificar.setFont(new Font("Jockey One", Font.PLAIN, 15));
		btn_modificar.setBorder(null);
		btn_modificar.setBackground(Color.BLACK);
		btn_modificar.setBounds(120, 93, 100, 28);
		panel.add(btn_modificar);
		
		list = new JList<String>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				categoriaId = Long.parseLong(list.getSelectedValue().split("->")[0].trim());
				textCategoria.setText(categoriaController.getCategoriaId(categoriaId).getCategoria());
			}
		});
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		list.setBounds(10, 132, 605, 516);
		panel.add(list);

		// Listar
		listar();
	}
}
