
package desk.mitienda.view.mensajes;

import javax.swing.*;
import java.awt.*;

public class Mensaje extends JFrame {
    private final Integer width = 250;
    private final Integer height = 150;
    public Mensaje(String mensaje) {
        setSize(width, height);
        setLocationRelativeTo(null);
        // setUndecorated(true);
        setLayout(new BorderLayout());
        setVisible(true);
        setTitle("Mensaje");
        add(new PanelMensaje(mensaje));
    }

/*    public static void main(String[] args) {
        new Mensaje("Error usuario repetido");
    }*/
}

class PanelMensaje extends JPanel {
    public PanelMensaje(String mensaje) {
        setBackground(Color.WHITE);
//        setLayout(null);
        JLabel label = new JLabel(mensaje);
        label.setForeground(Color.BLACK);
//        label.setBounds(50, 50, 100, 20);
        add(label);

    }
}


