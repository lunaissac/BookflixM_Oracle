package dao;

import Services.Encriptar;
import model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao extends Conexion {

    public Usuario startSession(String User, String Pass) throws Exception {
//        this.conectar();
        ResultSet rs;
        Usuario usuario = null;
        try {
            String sql = "SELECT\n"
                    + "	CODPER,\n"
                    + "	NOMPER,\n"
                    + "	APEPER,\n"
                    + "	DNIPER,\n"
                    + "	TIPPER,\n"
                    + "	GRADAUL,\n"
                    + "	SECCAUL \n"
                    + "FROM PERSONA \n"
                    + "WHERE USUPER LIKE ? AND PASSPER LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, Encriptar.encriptar(Pass));
            System.out.println("USER " + User);
            System.out.println("PASS " + Encriptar.encriptar(Pass));
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Aqui");
                usuario = new Usuario();
                usuario.setCodigo(rs.getString("CODPER"));
                usuario.setNombre(rs.getString("NOMPER"));
                usuario.setApellido(rs.getString("APEPER"));
                usuario.setCorreo(rs.getString("SECCAUL"));
                usuario.setDNI(rs.getString("DNIPER"));
                usuario.setCelular(rs.getString("GRADAUL"));
                usuario.setNivel(rs.getString("TIPPER"));
                usuario.setUsuario(User);
                usuario.setPassword(Pass);
//            } else {
//                System.out.println("No hay nada de regreso :'v");
            }
            return usuario;
        } catch (SQLException e) {
            System.out.println("Error al iniciar session");
            throw e;
        }
    }

}
