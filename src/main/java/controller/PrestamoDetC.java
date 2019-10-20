package controller;

import dao.LibroImpl;
import dao.PrestamoDetImpl;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.PrestamoDet;

@Named(value = "prestamodetC")
@SessionScoped
public class PrestamoDetC extends LibroC implements Serializable {

    private PrestamoDet prestamoDet;
    private PrestamoDetImpl dao;
    List<PrestamoDet> Listado;

////////////////////////////////////////////////////////////////////////////////
    private LibroImpl daoLibro;
    private LibroC librocontrol;

////////////////////////////////////////////////////////////////////////////////
    public PrestamoDetC() {
        prestamoDet = new PrestamoDet();
        dao = new PrestamoDetImpl();
        Listado = new ArrayList();
        daoLibro = new LibroImpl();
    }

//    public void CodigoPrestamos() {
//        try {
//            dao.UltimoRegistro(prestamoDet);
//        } catch (Exception e) {
//            System.out.println("Error al traer el código");
//        }
//
//    }
    
    
    
    
    public void registrarPrestamo() throws Exception {
        try {
            libro.setCODLIB(prestamoDet.getCODLIB());
            dao.UltimoRegistro(prestamoDet);
            Emprestar();
            if (Emprestar() == true) {
                dao.registra(prestamoDet);
                 FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Guardado.."));
                librocontrol.iniciar();               
//                obtenerCantidad();
                listar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Cantidad", "Insufuciente.."));
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void modificar() throws Exception {
        try {
            dao.modificar(prestamoDet);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado.."));
        } catch (Exception e) {
            throw e;
        }

    }

    public void eliminar(PrestamoDet pred) throws Exception {
        try {
            dao.eliminar(pred);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Eliminado.."));
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void listar() throws Exception {
        try {
            Listado = dao.ListarPreDet();
        } catch (Exception e) {
            throw e;
        }

    }

    public void limpiar() throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean Emprestar() throws Exception {
        int id = Integer.valueOf(prestamoDet.getCODLIB());
        int cantidadsolicitada = prestamoDet.getCATDET();
//        int cantidadActual = obtenerCantidad();

        int resultado;

        System.out.println("La cantidad solicitada es :" + cantidadsolicitada);
//        System.out.println("La cantidad del libro es :" + cantidadActual);

//        if (cantidadsolicitada <= cantidadActual) {
////            int cactual = obtenerCantidad();
//            resultado = cantidadActual - cantidadsolicitada;
//            
//            daoLibro.ActualizarStock(id, resultado);
//            
//            
//            System.out.println("Queda " + resultado + " Libros");
////            obtenerCantidad();
//            return true;
//        } else {
//            System.out.println("Cantidad Insuficiente");
//        }
        return false;
    }

    public PrestamoDet getPrestamoDet() {
        return prestamoDet;
    }

    public void setPrestamoDet(PrestamoDet prestamoDet) {
        this.prestamoDet = prestamoDet;
    }

    public void setDao(PrestamoDetImpl dao) {
        this.dao = dao;
    }

    public List<PrestamoDet> getListado() {
        return Listado;
    }

    public void setListado(List<PrestamoDet> Listado) {
        this.Listado = Listado;
    }

    public LibroC getLibrocontrol() {
        return librocontrol;
    }

    public void setLibrocontrol(LibroC librocontrol) {
        this.librocontrol = librocontrol;
    }

    public LibroImpl getDaoLibro() {
        return daoLibro;
    }

    public void setDaoLibro(LibroImpl daoLibro) {
        this.daoLibro = daoLibro;
    }
    
    
}
