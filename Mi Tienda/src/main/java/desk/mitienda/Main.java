package desk.mitienda;

import desk.mitienda.controller.UsuarioController;
import desk.mitienda.utils.FlyWay;
import desk.mitienda.view.IniciarSesionFrame;
import desk.mitienda.view.RegistroUsuarioFrame;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        FlyWay.migrate();
        
        if(usuarioController.usuarioRegistrado()) {
            System.out.println(usuarioController.usuarioRegistrado());
            new IniciarSesionFrame();
        } else {
            new RegistroUsuarioFrame();
        }
    }
}