package controller;

import Reportes.reporteP;
import dao.AutorImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Autor;

@Named(value = "autorC")
@SessionScoped
public class AutorC implements Serializable {

    private Autor autor;
    private AutorImpl dao;
    private List<Autor> listadoAutor;
    private List<Autor> listadoAutor2;

    private reporteP reporte;
    
    public AutorC() {
        dao = new AutorImpl();
        autor = new Autor();
        listadoAutor = new ArrayList();
        reporte = new reporteP();
    }

    @PostConstruct
    public void inicia() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        try {
            autor.setCODNACI(dao.obtenerCodigoNacionalidad(autor.getCODNACI()));
            dao.registrar(this.autor);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Completo..."));
        } catch (Exception e) {
            System.out.println("Error al registara en AutorC");
            throw e;            
        }
    }

    public void modificar() throws Exception {
        try {
            autor.setCODNACI(dao.obtenerCodigoNacionalidad(autor.getCODNACI()));
            dao.modificar(autor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Completo..."));
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Autor autor) throws Exception {
        try {
            dao.eliminar(autor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminador", "Completo..."));
            limpiar();
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listadoAutor = dao.listarAutor();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() throws Exception {
        try {
            this.autor.setNOMAUT("");
            this.autor.setAPEAUT("");
            this.autor.setCODNACI("");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reporteAutor() throws Exception {
        try {
            Map<String, Object> parameters = new HashMap();
            reporte.exportarAutorPDF(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeTextNacionalidad(String query) throws SQLException, Exception {
        AutorImpl daoAut = new AutorImpl();
        return daoAut.autocompleteNacionalidad(query);
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Autor> getListadoAutor() {
        return listadoAutor;
    }

    public void setListadoAutor(List<Autor> listadoAutor) {
        this.listadoAutor = listadoAutor;
    }

    public List<Autor> getListadoAutor2() {
        return listadoAutor2;
    }

    public void setListadoAutor2(List<Autor> listadoAutor2) {
        this.listadoAutor2 = listadoAutor2;
    }

    public AutorImpl getDao() {
        return dao;
    }

    public void setDao(AutorImpl dao) {
        this.dao = dao;
    }

    public reporteP getReporte() {
        return reporte;
    }

    public void setReporte(reporteP reporte) {
        this.reporte = reporte;
    }
    
    

}
