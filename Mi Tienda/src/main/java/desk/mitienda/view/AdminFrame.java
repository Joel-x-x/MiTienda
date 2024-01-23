package desk.mitienda.view;

import desk.mitienda.model.Rol;
import desk.mitienda.utils.Utilidades;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3509542713044310899L;
    private BarraLateral barraPanel;
    private JPanel panelPrincipal;
    private int panelAncho = 1080, panelAlto = 750;

    public static void main(String[] args) {
        new AdminFrame();
    }
    public AdminFrame() {
    	
    	setTitle("Mi Tienda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setLocationRelativeTo(null);

        // BarraPanel
        barraPanel = new BarraLateral(this);
        barraPanel.setPreferredSize(new Dimension(200, 750));

        // Panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setPreferredSize(new Dimension(1080, 750));
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Panel de inicio en base al rol
        if(Utilidades.getUsuario().getRol() == Rol.ADMINISTRADOR || Utilidades.getUsuario().getRol() == Rol.VENDEDOR) {
            cambiarPanel(new NotaVentaPanel(panelAncho, panelAlto));
        } else if(Utilidades.getUsuario().getRol() == Rol.BODEGUERO) {
            cambiarPanel(new ProductoPanel(panelAncho, panelAlto));
        }

        // Agregar BarraPanel y panelPrincipal al JFrame
        getContentPane().add(barraPanel, BorderLayout.WEST);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }

    public void cambiarPanel(JPanel nuevoPanel) {
        panelPrincipal.removeAll();
        panelPrincipal.add(nuevoPanel);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
//            case "Inicio":
//                cambiarPanel(new RegistrosDiariosPanel(panelAncho, panelAlto));
//                break;
            case "Usuarios":
                cambiarPanel(new UsuarioPanel(panelAncho, panelAlto));
                break;
        }
    }

}
