package graficos;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dao_año_libro extends Conexion {

    public List<año_libro> lstAñoLibro() throws SQLException, Exception {
        System.out.println("Llege al LISTADO");

        List<año_libro> lista;
        ResultSet rs;
        try {
            String sql = "SELECT\n"
                    + "	ANNLNZLIB AS AÑO,\n"
                    + "	SUM(CANTLIB) AS CANTIDAD\n"
                    + "FROM \n"
                    + "	LIBRO\n"
                    + "	GROUP BY ANNLNZLIB\n"
                    + "	ORDER BY ANNLNZLIB";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            año_libro al;
            while (rs.next()) {
                al = new año_libro();
                al.setANIO(rs.getInt("AÑO"));
                al.setCANTIDAD(rs.getInt("CANTIDAD"));
                lista.add(al);
                System.out.println("DATOS -->" + al.toString());
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;

    }

    public String Libros() {

        String libros = "";
        String sql = "SELECT\n"
                + "    SUM(CANTLIB) AS LIBROS\n"
                + "FROM LIBRO";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                libros = rs.getString("LIBROS");
            }
            st.close();
            rs.close();
            return libros;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Producto " + e);
        }
        return "";

    }

    public String cantLectores() {

        String lectores = "";
        String sql = "SELECT\n"
                + "    COUNT(CODPER) AS LECTORES\n"
                + "FROM PERSONA\n"
                + "    WHERE TIPPER='L'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lectores = rs.getString("LECTORES");
            }
            st.close();
            rs.close();
            return lectores;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Lectores " + e);
        }
        return "";

    }

    public String cantDevPendientes() {
        String DevPendientes = "";
        String sql = "SELECT \n"
                + "     SUM(CANTFAL) AS PENDIENTES\n"
                + "FROM PRESTAMO";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DevPendientes = rs.getString("PENDIENTES");
            }
            st.close();
            rs.close();
            return DevPendientes;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Pendientes " + e);
        }
        return "";

    }

    public String cantDevoluciones() {
        String Devoluciones = "";
        String sql = "SELECT \n"
                + "    SUM(CANTDET) AS DEVOLUCIONES\n"
                + "FROM PRESTAMO_DETALLE\n"
                + "    WHERE TIPDET='D'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Devoluciones = rs.getString("DEVOLUCIONES");
            }
            st.close();
            rs.close();
            return Devoluciones;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Pendientes " + e);
        }
        return "";

    }

    //METODO PARA CANTIDAD DE ALUMNOS POR AÑO
    public List<Alumnos> ListAlumnos() throws SQLException, Exception {
        List<Alumnos> lista;
        ResultSet rs;
        try {
            String sql = "SELECT \n"
                    + "    GRADAUL AS GRADO,\n"
                    + "    COUNT(CODPER)AS ALUMNOS\n"
                    + "FROM PERSONA\n"
                    + "    GROUP BY GRADAUL\n"
                    + "    ORDER BY GRADAUL";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Alumnos alumnos;
            while (rs.next()) {
                alumnos = new Alumnos();
                alumnos.setGRADO(rs.getInt("GRADO"));
                alumnos.setCANTIDAD(rs.getInt("ALUMNOS"));
                lista.add(alumnos);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

}
