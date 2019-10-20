package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {

    public Connection cnx = null;

    public Connection conectar() throws Exception {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@35.184.25.222:1521:XE";
            cnx = DriverManager.getConnection(BaseDeDatos, "dbBookFlix", "BookFlix-2019");
            if (cnx != null) {
                System.out.println("Conexión Correcta");
            } else {
                System.out.println("Error de Conex´ión");
            }
        } catch (Exception e) {
            System.out.println("Error de Conexión");
        }
        return cnx;
    }

    public void cerrar() throws Exception {
        if (cnx != null) {
            cnx.close();
        }
    }

//    public static void main(String[] args) throws Exception {
//        Conexion cn = new Conexion();
//        cn.conectar();
//        if (cn != null) {
//            JOptionPane.showMessageDialog(null, "Conectado");
//        } else {
//            JOptionPane.showMessageDialog(null, "Error al Conectar");
//        }
//    }
}
