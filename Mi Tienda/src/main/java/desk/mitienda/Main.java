package desk.mitienda;

import desk.mitienda.controller.UsuarioController;
import desk.mitienda.dao.UsuarioDao;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;
import desk.mitienda.utils.JPAUtils;
import desk.mitienda.view.IniciarSesionFrame;
import desk.mitienda.view.RegistroUsuario;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        FlyWay.migrate();
        if(usuarioController.usuarioRegistrado()) {
            new IniciarSesionFrame();
        } else {
            new RegistroUsuario();
        }
    }
}