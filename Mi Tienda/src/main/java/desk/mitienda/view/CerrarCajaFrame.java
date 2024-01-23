package desk.mitienda.view;

import desk.mitienda.controller.CajaController;
import desk.mitienda.model.Caja;
import desk.mitienda.utils.Utilidades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class CerrarCajaFrame extends JFrame {
    private JPanel contentPane;
    private JTextField textMontoCierre;
    private CajaInterfaz frame;
    private CajaController cajaController;
    private Caja caja;
    private JLabel labelValorCalculado;
    private JLabel labelDescuadre;

    public void aceptar() {
        Double montoCierre = null;

        if(!textMontoCierre.getText().equals("")) {
            montoCierre = Double.parseDouble(textMontoCierre.getText());
        } else {
            JOptionPane.showMessageDialog(null, "El monto cierre no puede ir vacio");
            return;
        }

        caja.cerrarCaja();

        cajaController.guardar(caja);

        frame.actualizar();
        dispose();
    }

    public void calcularValores() {
        if(textMontoCierre.getText().equals("")) {return;}
        caja.setMontoCierre(new BigDecimal(textMontoCierre.getText()));
        caja.calcularDescuadre();
        labelValorCalculado.setText(caja.getValorCalculado() + "");
        labelDescuadre.setText(caja.getDescuadre() + "");
    }

    public CerrarCajaFrame(CajaInterfaz frame) {
        this.frame = frame;
        cajaController = new CajaController();
        caja = cajaController.getCajaAbiertaUsuarioId(Utilidades.getUsuario().getId());
      
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 537, 347);
        setVisible(true);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(43, 45, 49));
        panel.setBounds(0, 0, 521, 308);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblFactura = new JLabel("Cerrar caja");
        lblFactura.setForeground(Color.WHITE);
        lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
        lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFactura.setBounds(0, 0, 521, 46);
        panel.add(lblFactura);

        JLabel labelEstablecimiento = new JLabel("");
        labelEstablecimiento.setForeground(Color.GRAY);
        labelEstablecimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelEstablecimiento.setBounds(729, 39, 70, 46);
        panel.add(labelEstablecimiento);

        JLabel Cantidad = new JLabel("Monto de cierre");
        Cantidad.setForeground(Color.WHITE);
        Cantidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
        Cantidad.setBounds(10, 57, 99, 14);
        panel.add(Cantidad);

        textMontoCierre = new JTextField();
        textMontoCierre.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		calcularValores();
        	}
        });
        textMontoCierre.setDragEnabled(true);
        textMontoCierre.setColumns(10);
        textMontoCierre.setBounds(10, 82, 501, 25);
        panel.add(textMontoCierre);

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
        btnAceptar.setBounds(222, 255, 100, 30);
        panel.add(btnAceptar);
        
        JLabel lblValorCalculador = new JLabel("Valor calculado");
        lblValorCalculador.setForeground(Color.WHITE);
        lblValorCalculador.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblValorCalculador.setBounds(10, 118, 129, 46);
        panel.add(lblValorCalculador);
        
        labelValorCalculado = new JLabel("0.0");
        labelValorCalculado.setForeground(Color.WHITE);
        labelValorCalculado.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelValorCalculado.setBounds(149, 118, 129, 46);
        panel.add(labelValorCalculado);
        
        JLabel lblDescuadre = new JLabel("Descuadre");
        lblDescuadre.setForeground(Color.WHITE);
        lblDescuadre.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDescuadre.setBounds(10, 175, 129, 46);
        panel.add(lblDescuadre);
        
        labelDescuadre = new JLabel("0.0");
        labelDescuadre.setForeground(Color.WHITE);
        labelDescuadre.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDescuadre.setBounds(149, 175, 129, 46);
        panel.add(labelDescuadre);


    }
}
