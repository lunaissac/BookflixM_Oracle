package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Autor;

public class AutorImpl extends Conexion implements IAutor {

    @Override
    public void registrar(Autor autor) throws Exception {
        String sql = "insert into AUTOR (NOMAUT,APEAUT,CODNACI) values (?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, autor.getNOMAUT());
            ps.setString(2, autor.getAPEAUT());
            ps.setString(3, autor.getCODNACI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Autor autor) throws Exception {
        String sql = "update AUTOR set NOMAUT=?,APEAUT=?,CODNACI=? where CODAUT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, autor.getNOMAUT());
            ps.setString(2, autor.getAPEAUT());
            ps.setString(3, autor.getCODNACI());
            ps.setInt(4, autor.getCODAUT());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Autor autor) throws Exception {
        String sql = "delete from AUTOR where CODAUT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, autor.getCODAUT());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Autor> listarAutor() throws Exception {
//        this.conectar();
        List<Autor> listado;
        Autor autor;
        String sql = "SELECT \n"
                + "    CODAUT,\n"
                + "    NOMAUT,\n"
                + "    APEAUT,\n"
                + "    GENTNACI AS NACIONALIDAD\n"
                + "FROM AUTOR\n"
                + "    INNER JOIN NACIONALIDAD\n"
                + "        ON NACIONALIDAD.CODNACI = AUTOR.CODNACI\n"
                + "    ORDER BY AUTOR.CODAUT";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                autor = new Autor();
                autor.setCODAUT(rs.getInt("CODAUT"));
                autor.setNOMAUT(rs.getString("NOMAUT"));
                autor.setAPEAUT(rs.getString("APEAUT"));
                autor.setCODNACI(rs.getString("NACIONALIDAD"));
                listado.add(autor);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public String obtenerCodigoNacionalidad(String Nacionalidad) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODNACI FROM NACIONALIDAD WHERE GENTNACI LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Nacionalidad); //Nacionalidad
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODNACI");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteNacionalidad(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select GENTNACI from NACIONALIDAD WHERE GENTNACI LIKE ? AND rownum < 6";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("GENTNACI"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
