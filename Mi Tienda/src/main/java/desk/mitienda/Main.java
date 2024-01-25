package desk.mitienda;

import desk.mitienda.controller.UsuarioController;
import desk.mitienda.utils.FlyWay;
import desk.mitienda.view.IniciarSesionFrame;
import desk.mitienda.view.RegistroUsuarioFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        FlyWay.migrate();
        
        if(usuarioController.usuarioRegistrado()) {
            SwingUtilities.invokeLater(() -> {
                new IniciarSesionFrame();
            });
        } else {
            new RegistroUsuarioFrame();
        }
    }
}