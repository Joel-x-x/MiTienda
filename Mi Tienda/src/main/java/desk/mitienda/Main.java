package desk.mitienda;

import desk.mitienda.dao.UsuarioDao;
import desk.mitienda.model.Usuario;
import desk.mitienda.utils.Estado;
import desk.mitienda.utils.FlyWay;
import desk.mitienda.utils.JPAUtils;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlyWay.migrate();
        FlyWay.repair();
    }
}