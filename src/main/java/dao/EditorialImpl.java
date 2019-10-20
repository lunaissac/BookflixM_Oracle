package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Editorial;

public class EditorialImpl extends Conexion implements IEditorial {

    @Override
    public void registrar(Editorial editorial) throws Exception {
        String sql = "insert into EDITORIAL (NOMEDIT) values (?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, editorial.getNOMEDIT());
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
    public void modificar(Editorial editorial) throws Exception {
        String sql = "UPDATE EDITORIAL SET NOMEDIT=? WHERE CODEDIT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, editorial.getNOMEDIT());
            ps.setInt(2, editorial.getCODEDIT());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en modificar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Editorial editorial) throws Exception {
        String sql = "delete from EDITORIAL where CODEDIT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1,editorial.getCODEDIT());
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
    public List<Editorial> listarEdit() throws Exception {
        this.conectar();
        List<Editorial> listado;
        Editorial edit;
        String sql = "SELECT * FROM EDITORIAL";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                edit = new Editorial();
                edit.setCODEDIT(rs.getInt("CODEDIT"));
                edit.setNOMEDIT(rs.getString("NOMEDIT"));
                listado.add(edit);
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
    
}
