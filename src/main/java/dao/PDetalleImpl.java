package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.PDetalle;
import model.Prestamo;

public class PDetalleImpl extends Conexion implements IPDetalle {

    @Override
    public void registrar(PDetalle devolucion) throws Exception {
        String sql = "insert into PRESTAMO_DETALLE (CODPRES,CANTDET,TIPDET) values (?,?,?)";
//        String sql = "insert into PRESTAMO.PRESTAMO_DETALLE (CODPRES,CANTDET,FECHENT,TIPDET) values (?,?,?,?)";

        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, devolucion.getCODPRESD());
            ps.setString(2, devolucion.getCANTDET());
//            ps.setString(3, devolucion.getFECHENT());
            ps.setString(3, "D");
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
    public void registrarprestamo(PDetalle devolucion) throws Exception {
        String sql = "insert into PRESTAMO_DETALLE (CODPRES,CANTDET,FECHENT,TIPDET) values (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, devolucion.getCODPRESD());
            ps.setString(2, devolucion.getCANTDET());
            ps.setString(3, devolucion.getFECHENT());
            ps.setString(4, "P");
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
    public void eliminar(PDetalle devolucion) throws Exception {
        String sql = "delete from PRESTAMO_DETALLE where CODDET=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, devolucion.getCODDET());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Eliminar " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<PDetalle> listarDevolucion() throws Exception {
        this.conectar();
        List<PDetalle> listado;
        PDetalle dev;
        String sql = "SELECT\n"
                + "	CODDET,\n"
                + "	PRESTAMO_DETALLE.CODPRES AS CODPRES,\n"
                + "	CONCAT(PERSONA.NOMPER,CONCAT(' ',PERSONA.APEPER)) AS LECTOR,\n"
                + "	LIBRO.TITLIB AS LIBRO,\n"
                + "	PRESTAMO.CANTFAL AS CANTDET,\n"
                + "    TO_CHAR(TO_DATE(FECHENT, 'DD-MM-YY'),'DD Mon YYYY','nls_date_language=spanish') AS FECHENT\n"
                + "FROM\n"
                + "	PRESTAMO_DETALLE\n"
                + "	INNER JOIN PRESTAMO\n"
                + "	ON PRESTAMO.CODPRES = PRESTAMO_DETALLE.CODPRES\n"
                + "	INNER JOIN LIBRO\n"
                + "	ON LIBRO.CODLIB = PRESTAMO.CODLIB\n"
                + "	INNER JOIN PERSONA\n"
                + "	ON PERSONA.CODPER = PRESTAMO.CODLECT\n"
                + "	WHERE TIPDET = 'P' AND PRESTAMO.CANTFAL != 0";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dev = new PDetalle();
                dev.setCODDET(rs.getInt("CODDET"));
                dev.setCODPRESD(rs.getInt("CODPRES"));
                dev.setLECTOR(rs.getString("LECTOR"));
                dev.setLIBRO(rs.getString("LIBRO"));
                dev.setCANTDET(rs.getString("CANTDET"));
                dev.setFECHENT(rs.getString("FECHENT"));
                listado.add(dev);
                System.out.println("Voy a Listar tus datos " + dev.toString());
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("No se pudo Listar");
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    @Override
    public int obtenerCantidadCabecera(List<Prestamo> listaprestamocabecera, PDetalle prestamos) throws Exception {
        for (Prestamo PresTemp : listaprestamocabecera) {
            if ((Integer.valueOf(PresTemp.getCODPRES())) == (prestamos.getCODPRESD())) {
                return PresTemp.getCANTFAL();
            }
        }
        return 0;
    }

    @Override
    public void ActualizarCabecera(int codigo, int cantidad) throws Exception {
        String sql = "update PRESTAMO set CANTFAL=? where CODPRES=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Actualizar Cabecera" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

}
