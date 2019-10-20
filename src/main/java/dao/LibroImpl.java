package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Libro;

public class LibroImpl extends Conexion implements ILibro {

    private Libro libro;

    @Override
    public void registrar(Libro libro) throws Exception {
        String sql = "insert into LIBRO (TITLIB,CANTLIB,ESTLIB,CODAUT,CODCAT,CODEDIT,ANNLNZLIB,PAGLIB,DESCLIB,FORINGLIB,DIRUBILIB) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, libro.getTITLIB());
            ps.setString(2, libro.getCANTLIB());
            ps.setString(3, libro.getESTLIB());
            ps.setString(4, libro.getCODAUT());
            ps.setString(5, libro.getCODCAT());
            ps.setString(6, libro.getCODEDIT());
            ps.setString(7, libro.getANNLNZLIB());
            ps.setString(8, libro.getPAGLIB());
            ps.setString(9, libro.getDESCLIB());
            ps.setString(10, libro.getFORINGLIB());
            ps.setString(11, libro.getDIRUBILIB());
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
    public void modificar(Libro libro) throws Exception {
        String sql = "update LIBRO set TITLIB=?, CANTLIB=?, ESTLIB=?, CODAUT=?, CODCAT=?, CODEDIT=?, ANNLNZLIB=?, PAGLIB=?, DESCLIB=?, FORINGLIB=? , DIRUBILIB=? where CODLIB=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, libro.getTITLIB());
            ps.setString(2, libro.getCANTLIB());
            ps.setString(3, libro.getESTLIB());
            ps.setString(4, libro.getCODAUT());
            ps.setString(5, libro.getCODCAT());
            ps.setString(6, libro.getCODEDIT());
            ps.setString(7, libro.getANNLNZLIB());
            ps.setString(8, libro.getPAGLIB());
            ps.setString(9, libro.getDESCLIB());
            ps.setString(10, libro.getFORINGLIB());
            ps.setString(11, libro.getDIRUBILIB());
            ps.setInt(12, Integer.valueOf(libro.getCODLIB()));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al moficar" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Libro libro) throws Exception {
        String sql = "delete from LIBRO where CODLIB=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(libro.getCODLIB()));
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
    public List<Libro> listarLib() throws Exception {
        this.conectar();
        List<Libro> listado;
        Libro libro;
        String sql = "SELECT \n"
                + "    CODLIB,\n"
                + "    TITLIB,\n"
                + "    AUTOR.CODAUT AS CODAUT2,\n"
                + "    CONCAT(NOMAUT,CONCAT(' ',APEAUT))AS CODAUT,\n"
                + "    CATEGORIA.CODCAT AS CODCAT2,\n"
                + "    NOMCAT AS CODCAT,\n"
                + "    EDITORIAL.CODEDIT AS CODEDIT2,\n"
                + "    NOMEDIT AS CODEDIT,\n"
                + "    CANTLIB,\n"
                + "    ANNLNZLIB,\n"
                + "    PAGLIB,\n"
                + "    DESCLIB,\n"
                + "    FORINGLIB,\n"
                + "    DIRUBILIB,\n"
                + "    ESTLIB\n"
                + "FROM LIBRO\n"
                + "    INNER JOIN AUTOR\n"
                + "        ON AUTOR.CODAUT = LIBRO.CODAUT\n"
                + "    INNER JOIN CATEGORIA\n"
                + "        ON CATEGORIA.CODCAT = LIBRO.CODCAT\n"
                + "    INNER JOIN EDITORIAL\n"
                + "        ON EDITORIAL.CODEDIT = LIBRO.CODEDIT";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                libro = new Libro();
                libro.setCODLIB(String.valueOf(rs.getInt("CODLIB")));
                libro.setTITLIB(rs.getString("TITLIB"));
                libro.setCANTLIB(String.valueOf(rs.getInt("CANTLIB")));
                libro.setESTLIB(rs.getString("ESTLIB"));
                libro.setCODAUT(rs.getString("CODAUT"));
                libro.setCODAUT2(rs.getInt("CODAUT2"));
                libro.setCODCAT(rs.getString("CODCAT"));
                libro.setCODCAT2(rs.getInt("CODCAT2"));
                libro.setCODEDIT(rs.getString("CODEDIT"));
                libro.setCODEDIT2(rs.getInt("CODEDIT2"));
                libro.setANNLNZLIB(rs.getString("ANNLNZLIB"));
                libro.setPAGLIB(rs.getString("PAGLIB"));
                libro.setDESCLIB(rs.getString("DESCLIB"));
                libro.setFORINGLIB(rs.getString("FORINGLIB"));
                libro.setDIRUBILIB(rs.getString("DIRUBILIB"));
                listado.add(libro);
                System.out.println(libro.toString());
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

    //-------------------------------------- AUTOCOMPLETADADO DE AUTOR ---------------------------------------------------
    public String obtenerCodigoAutor(String Autor) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT \n"
                    + "    CODAUT \n"
                    + "FROM AUTOR \n"
                    + "    WHERE CONCAT(NOMAUT,CONCAT(' ',APEAUT)) \n"
                    + "        LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Autor); //UBigeo
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODAUT");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteAutor(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(NOMAUT,CONCAT(' ',APEAUT)) NOMAUT \n"
                    + "FROM AUTOR \n"
                    + "WHERE NOMAUT LIKE ? AND ROWNUM <6";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("NOMAUT"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

//-------------------- AUTOCOMPLETADO DE CATEGORIA --------------------------------------------------------------------
    public String obtenerCodigoCategoria(String Categoria) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODCAT FROM CATEGORIA WHERE NOMCAT LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Categoria);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODCAT");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteCategoria(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT NOMCAT FROM CATEGORIA WHERE NOMCAT LIKE ? AND ROWNUM <6";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("NOMCAT"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

//-------------------- AUTOCOMPLETADO DE EDITORIAL --------------------------------------------------------------------
    public String obtenerCodigoEditorial(String Editorial) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODEDIT FROM EDITORIAL WHERE NOMEDIT LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Editorial);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODEDIT");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteEditorial(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT NOMEDIT from EDITORIAL WHERE NOMEDIT LIKE ? AND ROWNUM <6";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("NOMEDIT"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public int obtenerCantidad(List<Libro> listaLibro, Libro libro) throws Exception {
        System.out.println("Llege hasta el metodo para la comparación ");
        System.out.println("Ahora imprimire tu lista que me enviastes :");
        System.out.println(listaLibro.toString());
        System.out.println("Ahora imprimire tu modelo LIBRO :");
        System.out.println(libro.toString());

        for (Libro libroTemp : listaLibro) {
            if (libroTemp.getCODLIB().equals(libro.getCODLIB())) {
                return Integer.valueOf(libroTemp.getCANTLIB());
            }
        }
        return 0;
    }

    @Override
    public void ActualizarStock(int codigo, int cant) throws Exception {
        System.out.println("Llege a tu metodo Para Actualizar tu Stock");
        System.out.println("Este el CODLIBRO -->" + codigo);
        System.out.println("Esta es su cantidad -->" + cant);
        String sql = "update LIBRO set CANTLIB=? where CODLIB=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, String.valueOf(cant));
            ps.setInt(2, codigo);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Actualizar Stock" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public int obtenerCantidadUpdate(int codigoBuscar) throws Exception {
        System.out.println("Llege a tu metodo cantidad de libro Actual");
        System.out.println("Con este Codigo :" + codigoBuscar);
        ResultSet rs;
        try {
            String sql = "SELECT \n"
                    + "	LIBRO.CANTLIB AS CANTLIB\n"
                    + "FROM\n"
                    + "	PRESTAMO_DETALLE\n"
                    + "	INNER JOIN PRESTAMO\n"
                    + "		ON PRESTAMO.CODPRES = PRESTAMO_DETALLE.CODPRES\n"
                    + "	INNER JOIN LIBRO\n"
                    + "		ON LIBRO.CODLIB = PRESTAMO.CODLIB\n"
                    + "	WHERE PRESTAMO_DETALLE.CODPRES=? AND ROWNUM <2";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, codigoBuscar);
            rs = ps.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt("CANTLIB");
                return cantidad;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return 0;
    }

    public int obtenerCodigoUpdate(int codigoBuscar) throws Exception {
        System.out.println("Llege a tu metodo Para el codigo del Libro");
        System.out.println("Este es el codigo A Buscar :" + codigoBuscar);
        ResultSet rs;
        try {
            String sql = "SELECT\n"
                    + "	LIBRO.CODLIB AS CODLIB\n"
                    + "FROM\n"
                    + "	PRESTAMO_DETALLE\n"
                    + "	INNER JOIN PRESTAMO\n"
                    + "		ON PRESTAMO.CODPRES = PRESTAMO_DETALLE.CODPRES\n"
                    + "	INNER JOIN LIBRO\n"
                    + "		ON LIBRO.CODLIB = PRESTAMO.CODLIB\n"
                    + "	WHERE PRESTAMO_DETALLE.CODPRES=? AND ROWNUM <2";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, codigoBuscar);
            rs = ps.executeQuery();
            if (rs.next()) {
                int codigo = rs.getInt("CODLIB");
                return codigo;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return 0;
    }

    public Libro buscarLibroC(int codEquipo) {
        libro = new Libro();
        String sql = "SELECT\n"
                + "    CANTLIB,\n"
                + "    ESTLIB,\n"
                + "    NOMCAT,\n"
                + "    DIRUBILIB\n"
                + "FROM LIBRO\n"
                + "    INNER JOIN CATEGORIA\n"
                + "    ON CATEGORIA.CODCAT = LIBRO.CODCAT\n"
                + "    WHERE CODLIB="+codEquipo;
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                libro.setCANTLIB(rs.getString("CANTLIB"));
                libro.setESTLIB(rs.getString("ESTLIB"));
                libro.setCODCAT(rs.getString("NOMCAT"));
                libro.setDIRUBILIB(rs.getString("DIRUBILIB"));
            }
            return libro;
        } catch (Exception e) {
            System.out.println("Error al Buscar Libro");
            return null;
        }

    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

}
