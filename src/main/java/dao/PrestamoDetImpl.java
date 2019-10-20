package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.PrestamoDet;

public class PrestamoDetImpl extends Conexion implements IPrestamoDet {

    @Override
    public void registra(PrestamoDet PDet) throws Exception {
        String sql = "insert into PRESTAMO_DETALLE (CODPRES,CODLIB,CATDET) values (?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, PDet.getCODPRES());
            ps.setString(2, PDet.getCODLIB());
            ps.setInt(3, PDet.getCATDET());
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
    public void modificar(PrestamoDet PDet) throws Exception {
        String sql = "update PRESTAMO_DETALLE set CODPRES=?,CODLIB=?,CATDET=? where CODDET=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, PDet.getCODPRES());
            ps.setString(2, PDet.getCODLIB());
            ps.setInt(3, PDet.getCATDET());
            ps.setInt(4, PDet.getCODDET());
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
    public void eliminar(PrestamoDet PDet) throws Exception {
        String sql = "Delete from PRESTAMO_DETALLE where CODDET=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, PDet.getCODDET());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<PrestamoDet> ListarPreDet() throws Exception {
        this.conectar();
        List<PrestamoDet> listado;
        PrestamoDet presdet;
        String sql = "SELECT \n"
                + "	CONCAT(NOMPER,CONCAT(' ',APEPER)) AS CODPRES,\n"
                + "	LIBRO.TITLIB AS CODLIB,\n"
                + "	CANTDET \n"
                + "FROM \n"
                + "	PRESTAMO_DETALLE\n"
                + "INNER JOIN\n"
                + "	PRESTAMO\n"
                + "ON\n"
                + "	PRESTAMO.CODPRES = PRESTAMO_DETALLE.CODPRES\n"
                + "INNER JOIN\n"
                + "	PERSONA\n"
                + "ON\n"
                + "	PERSONA.CODPER = PRESTAMO.CODLECT\n"
                + "INNER JOIN\n"
                + "	LIBRO\n"
                + "ON\n"
                + "	LIBRO.CODLIB = PRESTAMO.CODLIB";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                presdet = new PrestamoDet();
                presdet.setCODPRES(rs.getInt("CODPRES"));
                presdet.setCODLIB(rs.getString("CODLIB"));
                presdet.setCATDET(rs.getInt("CANTDET"));
                listado.add(presdet);
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

    public void UltimoRegistro(PrestamoDet prestamodet) throws Exception {
        String sql = "SELECT CODPRES FROM PRESTAMO WHERE CODPRES = (SELECT MAX(CODPRES) FROM PRESTAMO)";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                prestamodet.setCODPRES(rs.getInt("CODPRES"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error al traer código de prestamo");
        }

    }
}
