package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Persona;
import Services.Encriptar;

public class PersonaImpl extends Conexion implements IPersona {

    private Persona persona;

    @Override
    public void registrar(Persona persona) throws Exception {
        String sql = "insert into PERSONA (NOMPER,APEPER,DNIPER,TIPPER,GRADAUL,SECCAUL,SECTAUL,CODUBI,USUPER,PASSPER) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDNIPER());
            ps.setString(4, persona.getTIPPER());
            ps.setString(5, persona.getGRADAUL());
            ps.setString(6, persona.getSECCAUL());
            ps.setString(7, persona.getSECTAUL());
            ps.setString(8, persona.getCODUBI());
            ps.setString(9, persona.getUSUPER());
            ps.setString(10, Encriptar.encriptar(persona.getPASSPER()));
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
    public void modificar(Persona persona) throws Exception {
        String sql = "update PERSONA set NOMPER=?,APEPER=?,DNIPER=?,TIPPER=?,GRADAUL=?,SECCAUL=?,SECTAUL=?,CODUBI=?,USUPER=?,PASSPER=? where CODPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDNIPER());
            ps.setString(4, persona.getTIPPER());
            ps.setString(5, persona.getGRADAUL());
            ps.setString(6, persona.getSECCAUL());
            ps.setString(7, persona.getSECTAUL());
            ps.setString(8, persona.getCODUBI());
            ps.setString(9, persona.getUSUPER());
            ps.setString(10, Encriptar.encriptar(persona.getPASSPER()));
            ps.setInt(11, persona.getCODPER());
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
    public void eliminar(Persona persona) throws Exception {
        String sql = "delete from PERSONA where CODPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, persona.getCODPER());
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
    public List<Persona> listarPer() throws Exception {
        this.conectar();
        List<Persona> listado;
        Persona per;
        String sql = "SELECT \n"
                + "	CODPER,NOMPER,\n"
                + "	APEPER,DNIPER,\n"
                + "	TIPPER,GRADAUL,\n"
                + "	SECCAUL,SECTAUL,\n"
                + "	USUPER,PASSPER, \n"
                + "	CONCAT(DISUBI,CONCAT(CONCAT(' ',PROUBI),CONCAT(' ',DEPUBI))) AS CODUBI\n"
                + "FROM PERSONA\n"
                + "	INNER JOIN UBIGEO\n"
                + "		ON UBIGEO.CODUBI = PERSONA.CODUBI";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                per = new Persona();
                per.setCODPER(rs.getInt("CODPER"));
                per.setNOMPER(rs.getString("NOMPER"));
                per.setAPEPER(rs.getString("APEPER"));
                per.setDNIPER(rs.getString("DNIPER"));
                per.setTIPPER(rs.getString("TIPPER"));
                per.setGRADAUL(rs.getString("GRADAUL"));
                per.setSECCAUL(rs.getString("SECCAUL"));
                per.setSECTAUL(rs.getString("SECTAUL"));
                per.setCODUBI(rs.getString("CODUBI"));
                per.setUSUPER(rs.getString("USUPER"));
                per.setPASSPER(rs.getString("PASSPER"));
                listado.add(per);
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

    public String obtenerCodigoPersona(String Ubigeo) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT \n"
                    + "    CODUBI\n"
                    + "FROM UBIGEO \n"
                    + "    WHERE CONCAT(DISUBI,CONCAT(CONCAT(' ',PROUBI),CONCAT(' ',DEPUBI))) LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Ubigeo); //UBigeo
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODUBI");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

//    public String obtenerNombredDistrito(String Distrito) throws SQLException, Exception {
//        this.conectar();
//        ResultSet rs;
//        try {
//            String sql = "SELECT CONCAT(DISUBI,' ',PROUBI,' ',DEPUBI) FROM PERSONA.UBIGEO WHERE CODUBI=?";
//            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            ps.setString(1, Distrito);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getString("CODUBI");
//            }
//            return null;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    public List<String> autocompleteUbigeo(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT \n"
                    + "    CONCAT(DISUBI,CONCAT(CONCAT(' ',PROUBI),CONCAT(' ',DEPUBI))) DISUBI \n"
                    + "FROM UBIGEO \n"
                    + "    WHERE DISUBI LIKE ? AND ROWNUM <11";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("DISUBI"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Persona> listarPerTip() throws Exception {
        this.conectar();
        List<Persona> listado;
        Persona per;
        String sql = "SELECT \n"
                + "	CODPER,NOMPER,APEPER \n"
                + "FROM \n"
                + "	PERSONA\n"
                + "WHERE TIPPER = 'B'";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                per = new Persona();
                per.setCODPER(rs.getInt("CODPER"));
                per.setNOMPER(rs.getString("NOMPER"));
                per.setAPEPER(rs.getString("APEPER"));
                listado.add(per);
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

    @Override
    public List<Persona> listarLector() throws Exception {
        List<Persona> listado;
        Persona per;
        String sql = "SELECT \n"
                + " 	CODPER,\n"
                + " 	NOMPER,\n"
                + " 	APEPER,\n"
                + " 	DNIPER,\n"
                + " 	TIPPER,\n"
                + " 	GRADAUL,\n"
                + " 	SECCAUL,\n"
                + " 	SECTAUL,\n"
                + " 	USUPER,\n"
                + " 	PASSPER,\n"
                + " 	CONCAT(DISUBI,CONCAT(CONCAT(' ',PROUBI),CONCAT(' ',DEPUBI))) AS CODUBI\n"
                + " FROM \n"
                + " 	PERSONA\n"
                + " 	INNER JOIN UBIGEO\n"
                + " 		ON UBIGEO.CODUBI = PERSONA.CODUBI\n"
                + " 	WHERE TIPPER = 'L'\n"
                + " 	ORDER BY PERSONA.NOMPER ASC";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                per = new Persona();
                per.setCODPER(rs.getInt("CODPER"));
                per.setNOMPER(rs.getString("NOMPER"));
                per.setAPEPER(rs.getString("APEPER"));
                per.setDNIPER(rs.getString("DNIPER"));
                per.setTIPPER(rs.getString("TIPPER"));
                per.setGRADAUL(rs.getString("GRADAUL"));
                per.setSECCAUL(rs.getString("SECCAUL"));
                per.setSECTAUL(rs.getString("SECTAUL"));
                per.setUSUPER(rs.getString("USUPER"));
                per.setPASSPER(rs.getString("PASSPER"));
                per.setCODUBI(rs.getString("CODUBI"));
                listado.add(per);
                System.out.println("Los Lectores :" + per.toString());
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

    //METODO PARA TRAER AL LECTOR POR CODIGO
    public Persona buscarLectorC(int codLector) {
        persona = new Persona();
        String sql = "SELECT\n"
                + "    DNIPER,\n"
                + "    GRADAUL,\n"
                + "    SECCAUL,\n"
                + "    SECTAUL\n"
                + "FROM PERSONA\n"
                + "    WHERE TIPPER='L' AND CODPER=" + codLector;
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setGRADAUL(rs.getString("GRADAUL"));
                persona.setSECCAUL(rs.getString("SECCAUL"));
                persona.setSECTAUL(rs.getString("SECTAUL"));
            }
            return persona;
        } catch (Exception e) {
            System.out.println("Error al Buscar Lector en DAO");
            return null;
        }

    }

    public boolean actualizarPass(Persona persona) throws Exception {
        String sql = "UPDATE PERSONA \n"
                + "SET PASSPER=?\n"
                + "    WHERE USUPER=? \n"
                + "        AND DNIPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Encriptar.encriptar(persona.getPASSPER()));
            ps.setString(2, persona.getUSUPER());
            ps.setString(3, persona.getDNIPER());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar Pass " + e.getMessage());
            return false;
        } finally {
            this.cerrar();
        }

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
