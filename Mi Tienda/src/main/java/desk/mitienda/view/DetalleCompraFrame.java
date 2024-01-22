package desk.mitienda.view;

import desk.mitienda.model.Compra;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class DetalleCompraFrame extends JFrame {
    private JPanel contentPane;
    private JTextField textCantidad;
    private JTextField textPrecioUnitario;
    private GenerarFormularioProductoInterfaz frame;
    
    public void aceptar() {
        Double cantidad = null;
        BigDecimal precioUnitario = null;

        if(!textCantidad.getText().equals("")) {
            cantidad = Double.parseDouble(textCantidad.getText());
        }

        if(!textPrecioUnitario.getText().equals("")) {
            precioUnitario = new BigDecimal(textPrecioUnitario.getText());
        }


        frame.actualizarDetalle(cantidad, precioUnitario);
        dispose();
    }

    public DetalleCompraFrame(GenerarFormularioProductoInterfaz frame) {
        this.frame = frame;
      
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 271, 237);
        setVisible(true);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(43, 45, 49));
        panel.setBounds(0, 0, 255, 206);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblFactura = new JLabel("Producto");
        lblFactura.setForeground(Color.WHITE);
        lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
        lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFactura.setBounds(0, 0, 256, 46);
        panel.add(lblFactura);

        JLabel labelEstablecimiento = new JLabel("");
        labelEstablecimiento.setForeground(Color.GRAY);
        labelEstablecimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelEstablecimiento.setBounds(729, 39, 70, 46);
        panel.add(labelEstablecimiento);

        JLabel Cantidad = new JLabel("Cantidad");
        Cantidad.setForeground(Color.WHITE);
        Cantidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
        Cantidad.setBounds(10, 57, 99, 14);
        panel.add(Cantidad);

        textCantidad = new JTextField();
        textCantidad.setDragEnabled(true);
        textCantidad.setColumns(10);
        textCantidad.setBounds(10, 82, 99, 25);
        panel.add(textCantidad);

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
        btnAceptar.setBounds(81, 145, 100, 30);
        panel.add(btnAceptar);
        
        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setForeground(Color.WHITE);
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblPrecio.setBounds(146, 57, 99, 14);
        panel.add(lblPrecio);
        
        textPrecioUnitario = new JTextField();
        textPrecioUnitario.setDragEnabled(true);
        textPrecioUnitario.setColumns(10);
        textPrecioUnitario.setBounds(146, 82, 99, 25);
        panel.add(textPrecioUnitario);
    }
}
