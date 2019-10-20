package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.PDetalle;
import model.Prestamo;

public class PrestamoImpl extends Conexion implements IPrestamo {

    @Override
    public void registrar(Prestamo prestamo) throws Exception {
        String sql = "insert into PRESTAMO (CODBIBL,CODLECT,CANTFAL,CODLIB) values (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, prestamo.getCODBIBL());
            ps.setString(2, prestamo.getCODLECT());
//            ps.setString(3, prestamo.getFSALPRES());
//            ps.setString(4, prestamo.getFENTPRES());
            ps.setInt(3, prestamo.getCANTFAL());
            ps.setInt(4, prestamo.getCODLIB());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al registrar " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Prestamo prestamo) throws Exception {
        String sql = "update PRESTAMO set CODBIBL=?,CODLECT=?,FSALPRES=?,FENTPRES=?,CANTFAL=?,CODLIB=? where CODPRES=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, prestamo.getCODBIBL());
            ps.setString(2, prestamo.getCODLECT());
            ps.setString(3, prestamo.getFSALPRES());
            ps.setString(4, prestamo.getFENTPRES());
            ps.setInt(5, prestamo.getCANTFAL());
            ps.setInt(6, prestamo.getCODLIB());
            ps.setString(7, prestamo.getCODPRES());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Prestamo prestamo) throws Exception {
        String sql = "delete from PRESTAMO where CODPRES=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, prestamo.getCODPRES());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Prestamo> listarPre() throws Exception {
        this.conectar();
        List<Prestamo> listado;
        Prestamo pres;
        String sql = "SELECT\n"
                + "    CODPRES,\n"
                + "    CONCAT(PERSONA.NOMPER,CONCAT(' ',PERSONA.APEPER)) AS CODBIBL,\n"
                + "    CONCAT(LECTOR.NOMPER,CONCAT(' ',LECTOR.APEPER)) AS  CODLECT,\n"
                + "    TO_CHAR(TO_DATE(FSALPRES, 'DD-MM-YY'),'DD Mon YYYY','nls_date_language=spanish') AS FSALPRES,\n"
                + "    TO_CHAR(TO_DATE(FENTPRES, 'DD-MM-YY'),'DD Mon YYYY','nls_date_language=spanish') AS FENTPRES,\n"
                + "    CANTFAL,\n"
                + "    PRESTAMO.CODLIB,\n"
                + "    TITLIB AS NAMELIBRO\n"
                + "FROM PRESTAMO\n"
                + "    INNER JOIN PERSONA\n"
                + "        ON PERSONA.CODPER = PRESTAMO.CODBIBL\n"
                + "    INNER JOIN PERSONA  LECTOR\n"
                + "        ON LECTOR.CODPER = PRESTAMO.CODLECT\n"
                + "    INNER JOIN LIBRO\n"
                + "        ON LIBRO.CODLIB = PRESTAMO.CODLIB\n"
                + "    WHERE CANTFAL != 0\n"
                + "    ORDER BY FSALPRES DESC";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pres = new Prestamo();
                pres.setCODPRES(String.valueOf(rs.getInt("CODPRES")));
                pres.setCODBIBL(rs.getString("CODBIBL"));
                pres.setCODLECT(rs.getString("CODLECT"));
                pres.setFSALPRES(rs.getString("FSALPRES"));
                pres.setFENTPRES(rs.getString("FENTPRES"));
                pres.setCANTFAL(rs.getInt("CANTFAL"));
                pres.setCODLIB(rs.getInt("CODLIB"));
                pres.setNAMELIBRO(rs.getString("NAMELIBRO"));
                listado.add(pres);
                System.out.println("Imprimire tus datos OK");
                System.out.println(pres.toString());
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error al listar");
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }
//-------------------------BIBLIOTECARIO ----------------------------------

//    public String obtenerCodigoBibliotecario(String Bibliotecario) throws SQLException, Exception {
//        this.conectar();
//        ResultSet rs;
//        try {
//            String sql = "SELECT \n"
//                    + "    CODPER \n"
//                    + "FROM PERSONA \n"
//                    + "    WHERE CONCAT(NOMPER,CONCAT(' ',APEPER)) LIKE ?";
//            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            ps.setString(1, Bibliotecario); //UBigeo
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getString("CODPER");
//            }
//            return null; //sql
//        } catch (SQLException e) {
//            throw e;
//        }
//    }
//    public List<String> autocompleteBibliotecario(String Consulta) throws SQLException, Exception {
//        this.conectar();
//        ResultSet rs;
//        List<String> Lista;
//        try {
//            String sql = "SELECT \n"
//                    + "	CONCAT(NOMPER,CONCAT(' ',APEPER)) NOMPER \n"
//                    + "FROM PERSONA \n"
//                    + "	WHERE NOMPER LIKE ? AND TIPPER LIKE 'B' AND ROWNUM <6";
//            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            ps.setString(1, "%" + Consulta + "%");
//            Lista = new ArrayList<>();
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Lista.add(rs.getString("NOMPER"));
//            }
//            return Lista;
//        } catch (SQLException e) {
//            throw e;
//        }
//    }
    //---------------LECTOR--------------
    public String obtenerCodigoLector(String Lector) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT \n"
                    + "    CODPER \n"
                    + "FROM PERSONA \n"
                    + "    WHERE CONCAT(NOMPER,CONCAT(' ',APEPER)) LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Lector); //UBigeo
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODPER");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteLector(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT  \n"
                    + "	CONCAT(NOMPER,CONCAT(' ',APEPER)) NOMPER \n"
                    + "FROM PERSONA \n"
                    + "	WHERE NOMPER LIKE ? and TIPPER LIKE 'L' AND ROWNUM <6";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("NOMPER"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    //Metodo para tarer el ultimo registro, que lo usaremos para registra en el detalle prestamo
    public void UltimoRegistro(PDetalle pdetalle) throws Exception {
        String sql = "SELECT \n"
                + "	CODPRES,\n"
                + "	CANTFAL,\n"
                + "	FSALPRES \n"
                + "FROM PRESTAMO\n"
                + "	WHERE CODPRES = (SELECT MAX(CODPRES) FROM PRESTAMO)";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pdetalle.setCODPRESD(rs.getInt("CODPRES"));
                pdetalle.setCANTDET(rs.getString("CANTFAL"));
                pdetalle.setFECHENT(rs.getString("FSALPRES"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error al traer código de prestamo");
        }

    }
}
