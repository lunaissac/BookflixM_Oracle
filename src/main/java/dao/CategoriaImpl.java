package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;


public class CategoriaImpl extends Conexion implements ICategoria{

    @Override
    public void registrar(Categoria categoria) throws Exception {
       String sql = "insert into CATEGORIA (NOMCAT) values (?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNOMCAT());
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
    public void modificar(Categoria categoria) throws Exception {
           String sql = "update CATEGORIA set NOMCAT=? where CODCAT=?";
        try {
            PreparedStatement ps =this.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNOMCAT());
            ps.setInt(2, categoria.getCODCAT());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al moficar" + e.getMessage());
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Categoria categoria) throws Exception {
    String sql = "delete from CATEGORIA where CODCAT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, categoria.getCODCAT());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public List<Categoria> listarCat() throws Exception {
    this.conectar();
        List<Categoria> listado;
        Categoria categoria;
        String sql = "select * from CATEGORIA ORDER BY CODCAT";
        try {
            listado = new ArrayList();
            Statement st =this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                categoria = new Categoria();
                categoria.setCODCAT(rs.getInt("CODCAT"));
                categoria.setNOMCAT(rs.getString("NOMCAT"));
                listado.add(categoria);  
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
        return listado;
    }
    
}

    