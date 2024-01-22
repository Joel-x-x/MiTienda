package desk.mitienda.view;

import desk.mitienda.controller.IvaController;
import desk.mitienda.controller.UsuarioController;
import desk.mitienda.model.Iva;
import desk.mitienda.utils.Estado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class IvaFrame extends JFrame{

    private JPanel contentPane;
    private JTextField textIva;
    private JList<String> list;
    private IvaController ivaController;

    public void agregarIva() {
        Iva iva = Iva.builder().fecha(LocalDate.now())
                .iva(new BigDecimal(textIva.getText()))
                .build();

        Estado estado = ivaController.guardar(iva);

        JOptionPane.showMessageDialog(null, estado.getMensaje());

        listar();
        textIva.setText("");
    }

    public void listar() {
        List<Iva> ivas = ivaController.listar();

        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Iva iva : ivas) {
            listModel.addElement(iva.getIva() + "     " + iva.getFecha());
        }
        list.setModel(listModel);
    }

    public IvaFrame() {
        // Controllers
        ivaController = new IvaController();

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

        list = new JList<String>();
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

        listar();
    }
}
