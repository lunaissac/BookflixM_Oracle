package graficos;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dao_prestamo_devolucion extends Conexion {

    public List<prestamo_devolucion> lstPrestamoDevolucion() throws SQLException, Exception {
        List<prestamo_devolucion> lista;
        ResultSet rs;
        try {

            String sql = "SELECT\n"
                    + "	CASE TIPDET\n"
                    + "		WHEN 'D' THEN 'Devolución'\n"
                    + "		WHEN 'P' THEN 'Prestamo'\n"
                    + "		ELSE 'No definido'\n"
                    + "	END AS TIPO,\n"
                    + "	CASE TIPDET\n"
                    + "		WHEN 'D' THEN SUM(CANTDET)\n"
                    + "		WHEN 'P' THEN SUM(CANTDET)\n"
                    + "		ELSE 0\n"
                    + "	END AS CANTIDAD\n"
                    + "FROM \n"
                    + "	PRESTAMO_DETALLE\n"
                    + "GROUP BY TIPDET";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            prestamo_devolucion pd;
            while (rs.next()) {
                pd = new prestamo_devolucion();
                pd.setTIPO(rs.getString("TIPO"));
                pd.setCANTIDAD(rs.getInt("CANTIDAD"));
                lista.add(pd);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;

    }

}
