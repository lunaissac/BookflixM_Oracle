package controller;

import Reportes.reporteP;
import dao.CategoriaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Categoria;

@Named(value = "categoriaC")
@SessionScoped
public class CategoriaC implements Serializable{
    
    private Categoria categoria;
    private CategoriaImpl dao;
    private List<Categoria> listadoCategoria;
    private List<Categoria> listadoCategoria2;
    
    private reporteP reporte;
    
    public CategoriaC(){
        dao = new CategoriaImpl();
        categoria = new Categoria();
        listadoCategoria = new ArrayList();
        reporte = new reporteP();
    }
        @PostConstruct
    public void inicia(){
        try {
            listar();
        } catch (Exception e) {
        }
    }
    
    public void registrar() throws Exception{
        try {
            dao.registrar(this.categoria);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro","Completo..."));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificar() throws Exception{
        try {
            dao.modificar(categoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado","Completo..."));
            listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Categoria categoria) throws Exception{
        try {
            dao.eliminar(categoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado","Completo..."));
            limpiar();
            listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception{
        try {
            listadoCategoria = dao.listarCat();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void limpiar() throws Exception{
        try {
            this.categoria.setNOMCAT("");
        } catch (Exception e) {
            throw e;
        }
    }
    
     public void reporteCategoria() throws Exception {       
        try {
            Map<String, Object> parameters = new HashMap();
            reporte.exportarCategoriaPDF(parameters);
        } catch (Exception e) {
            System.out.println("Error al Generar su reporte");
            throw e;
        }
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListadoCat() {
        return listadoCategoria;
    }

    public void setListadoCategoria(List<Categoria> listadoCategoria) {
        this.listadoCategoria = listadoCategoria;
    }

    
    public List<Categoria> getListadoCategoria2() {
        return listadoCategoria2;
    }

    public void setListadoCategoria2(List<Categoria> listadoCategoria2) {
        this.listadoCategoria2 = listadoCategoria2;
    }
    
    public CategoriaImpl getDao() {
        return dao;
    }

    public void setDao(CategoriaImpl dao) {
        this.dao = dao;
    }

    public reporteP getReporte() {
        return reporte;
    }

    public void setReporte(reporteP reporte) {
        this.reporte = reporte;
    }
    
}