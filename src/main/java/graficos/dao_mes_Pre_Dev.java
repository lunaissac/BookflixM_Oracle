package graficos;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dao_mes_Pre_Dev extends Conexion {

    public List<mes_Pre_Dev> lstPrestamoDevolucion() throws SQLException, Exception {
        List<mes_Pre_Dev> lista;
        ResultSet rs;
        try {

            String sql = "SELECT	\n"
                    + "	CASE EXTRACT(MONTH FROM TO_DATE(FECHENT, 'YYYY-MM-DD'))\n"
                    + "		WHEN 1 THEN 'Enero'\n"
                    + "		WHEN 2 THEN 'Febrero'\n"
                    + "		WHEN 3 THEN 'Marzo'\n"
                    + "		WHEN 4 THEN 'Abril'\n"
                    + "		WHEN 5 THEN 'Mayo'\n"
                    + "		WHEN 6 THEN 'Junio'\n"
                    + "		WHEN 7 THEN 'Julio'\n"
                    + "		WHEN 8 THEN 'Agosto'\n"
                    + "		WHEN 9 THEN 'Septiembre'\n"
                    + "		WHEN 10 THEN 'Octubre'\n"
                    + "		WHEN 11 THEN 'Noviembre'\n"
                    + "		WHEN 12 THEN 'Diciembre'\n"
                    + "		ELSE 'No Definido'\n"
                    + "	END AS MES,\n"
                    + "	CASE EXTRACT(MONTH FROM TO_DATE(FECHENT, 'YYYY-MM-DD'))\n"
                    + "		WHEN 1 THEN SUM(CANTDET)\n"
                    + "		WHEN 2 THEN SUM(CANTDET)\n"
                    + "		WHEN 3 THEN SUM(CANTDET)\n"
                    + "		WHEN 4 THEN SUM(CANTDET)\n"
                    + "		WHEN 5 THEN SUM(CANTDET)\n"
                    + "		WHEN 6 THEN SUM(CANTDET)\n"
                    + "		WHEN 7 THEN SUM(CANTDET)\n"
                    + "		WHEN 8 THEN SUM(CANTDET)\n"
                    + "		WHEN 9 THEN SUM(CANTDET)\n"
                    + "		WHEN 10 THEN SUM(CANTDET)\n"
                    + "		WHEN 11 THEN SUM(CANTDET)\n"
                    + "		WHEN 12 THEN SUM(CANTDET)\n"
                    + "		ELSE 0\n"
                    + "	END AS CANTIDAD,\n"
                    + "	TIPDET AS TIPO\n"
                    + "FROM\n"
                    + "	PRESTAMO_DETALLE\n"
                    + "	GROUP BY EXTRACT(MONTH FROM TO_DATE(FECHENT, 'YYYY-MM-DD')),TIPDET\n"
                    + "    ORDER BY EXTRACT(MONTH FROM TO_DATE(FECHENT, 'YYYY-MM-DD'))";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            mes_Pre_Dev mpd;
            while (rs.next()) {
                mpd = new mes_Pre_Dev();
                mpd.setMES(rs.getString("MES"));
                mpd.setCANTIDAD(rs.getInt("CANTIDAD"));
                mpd.setTIPO(rs.getString("TIPO"));
                lista.add(mpd);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;

    }
    
//    public String cantLibros() {
//        String CLibros = "";
//        String sql = "SELECT \n"
//                + "    SUM(CODLIB) AS LIBROS\n"
//                + "FROM LIBRO;";
//        try {
//            Statement st = this.conectar().createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                CLibros = rs.getString("LIBROS");
//            }
//            st.close();
//            rs.close();
//            return CLibros;
//        } catch (Exception e) {
//            System.out.println("Error al traer Cant. Libros " + e);
//        }
//        return "";
//
//    }

}
