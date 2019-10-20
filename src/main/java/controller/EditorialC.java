package controller;

import Reportes.reporteP;
import dao.EditorialImpl;
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
import model.Editorial;

@Named(value = "editorialC")
@SessionScoped
public class EditorialC implements Serializable {

    private Editorial editorial;
    private EditorialImpl dao;
    private List<Editorial> listadoEdit;
    private List<Editorial> listadoEdit2;
    private reporteP reporte;

    public EditorialC() {
        editorial = new Editorial();
        dao = new EditorialImpl();
        listadoEdit = new ArrayList();
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
            dao.registrar(editorial);
            System.out.println("Entrar a registro");
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado.."));
        } catch (Exception e) {
            throw e;
        }

    }

    public void modificar() throws Exception {
        try {
            dao.modificar(editorial);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado.."));
            listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void eliminar(Editorial edit) throws Exception {
        try {
            System.out.println("Llamado al metodo eliminar");
            dao.eliminar(edit);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Eliminado.."));
            limpiar();
            listar();
        } catch (Exception e) {
        }

    }

    public void listar() throws Exception {
        try {
            listadoEdit = dao.listarEdit();
        } catch (Exception e) {
            throw e;
        }

    }

    public void limpiar() throws Exception {
        try {
            this.editorial.setNOMEDIT(null);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void reporteEdit() throws Exception{
         try {
            Map<String, Object> parameters = new HashMap();
            reporte.exportarEditPDF(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public List<Editorial> getListadoEdit() {
        return listadoEdit;
    }

    public void setListadoEdit(List<Editorial> listadoEdit) {
        this.listadoEdit = listadoEdit;
    }

    public List<Editorial> getListadoEdit2() {
        return listadoEdit2;
    }

    public void setListadoEdit2(List<Editorial> listadoEdit2) {
        this.listadoEdit2 = listadoEdit2;
    }

    public EditorialImpl getDao() {
        return dao;
    }

    public void setDao(EditorialImpl dao) {
        this.dao = dao;
    }

    public reporteP getReporte() {
        return reporte;
    }

    public void setReporte(reporteP reporte) {
        this.reporte = reporte;
    }

    
}
