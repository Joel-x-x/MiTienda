package desk.mitienda.view;

import desk.mitienda.controller.CajaController;
import desk.mitienda.model.Caja;
import desk.mitienda.utils.Utilidades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class AbrirCajaFrame extends JFrame {
    private JPanel contentPane;
    private JTextField textMontoApertura;
    private CajaInterfaz frame;
    private CajaController cajaController;
    
    public void aceptar() {
        Double montoApertura = null;

        if(!textMontoApertura.getText().equals("")) {
            montoApertura = Double.parseDouble(textMontoApertura.getText());
        } else {
            JOptionPane.showMessageDialog(null, "El monto apertura no puede ir vacio");
            return;
        }

        Caja caja = new Caja();
        caja.abrirCaja(new BigDecimal(montoApertura), Utilidades.getUsuario());

        cajaController.guardar(caja);

        frame.actualizar();
        dispose();
    }

    public AbrirCajaFrame(CajaInterfaz frame) {
        this.frame = frame;
        cajaController = new CajaController();
      
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 384, 237);
        setVisible(true);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(43, 45, 49));
        panel.setBounds(0, 0, 368, 206);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblFactura = new JLabel("Producto");
        lblFactura.setForeground(Color.WHITE);
        lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
        lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFactura.setBounds(0, 0, 368, 46);
        panel.add(lblFactura);

        JLabel labelEstablecimiento = new JLabel("");
        labelEstablecimiento.setForeground(Color.GRAY);
        labelEstablecimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelEstablecimiento.setBounds(729, 39, 70, 46);
        panel.add(labelEstablecimiento);

        JLabel Cantidad = new JLabel("Monto apertura");
        Cantidad.setForeground(Color.WHITE);
        Cantidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
        Cantidad.setBounds(10, 57, 99, 14);
        panel.add(Cantidad);

        textMontoApertura = new JTextField();
        textMontoApertura.setDragEnabled(true);
        textMontoApertura.setColumns(10);
        textMontoApertura.setBounds(10, 82, 348, 25);
        panel.add(textMontoApertura);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aceptar();
        	}
        });
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorder(null);
        btnAceptar.setBackground(new Color(46, 56, 64));
        btnAceptar.setBounds(147, 146, 100, 30);
        panel.add(btnAceptar);
    }
}
