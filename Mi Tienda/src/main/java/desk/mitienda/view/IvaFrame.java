package desk.mitienda.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gym.controller.FacturaController;
import com.gym.model.Administrador;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IvaFrame extends JFrame {
	private int administrador_id;
	private FacturaController facturaController;
	
	private static final long serialVersionUID = 1297138597343405588L;
	private JPanel contentPane;
	private JTextField textIva;
	private JList<String> list;
	
	public void agregarIva() {
		if(!facturaController.actulizarIva(Double.parseDouble(textIva.getText()), administrador_id)) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error");
		} else {
			JOptionPane.showMessageDialog(null, "Iva actualizado");
		}
	}

	public IvaFrame(DefaultListModel<String> listaIvas) {
		administrador_id = new Administrador().getId();
		facturaController = new FacturaController();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 434, 411);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHistorialDePrecios = new JLabel("HISTORIAL DE IVAS");
		lblHistorialDePrecios.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDePrecios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHistorialDePrecios.setBounds(0, 11, 434, 37);
		panel.add(lblHistorialDePrecios);
		
		list = new JList<String>(listaIvas);
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		list.setBounds(12, 107, 412, 289);
		panel.add(list);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Actulizar IVA:");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(10, 75, 79, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textIva = new JTextField();
		textIva.setColumns(10);
		textIva.setBounds(90, 69, 137, 25);
		panel.add(textIva);
		
		JButton btnActualizar = new JButton("Agregar IVA");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarIva();
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(new Color(46, 56, 64));
		btnActualizar.setBounds(237, 70, 123, 25);
		panel.add(btnActualizar);
	}
}
