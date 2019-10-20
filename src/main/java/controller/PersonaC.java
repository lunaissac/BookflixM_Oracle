package controller;

import Reportes.reporteP;
import dao.PersonaImpl;
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
import model.Persona;
import org.primefaces.component.datatable.DataTable;

@Named(value = "personaC")
@SessionScoped

public class PersonaC implements Serializable {

    private Persona persona;
    private PersonaImpl dao;
    private List<Persona> listadoper;
    private List<Persona> listadoper2;

    private List<Persona> listadoLector;
    private reporteP reporte;

    public PersonaC() {
        persona = new Persona();
        dao = new PersonaImpl();
        listadoper = new ArrayList();
        reporte = new reporteP();
        listadoLector = new ArrayList();
    }

    @PostConstruct
    public void Iniciar() {
        try {
            listar();
            listarLector();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        try {
            persona.setCODUBI(dao.obtenerCodigoPersona(persona.getCODUBI()));
            dao.registrar(persona);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado.."));
        } catch (Exception e) {
            throw e;
        }

    }

    public void modificar() throws Exception {
        try {
            persona.setCODUBI(dao.obtenerCodigoPersona(persona.getCODUBI()));
            //persona.setCODUBI(dao.obtenerNombredDistrito(persona.getCODUBI()));
            dao.modificar(persona);
            resetearFitrosTabla("form:dtTabla");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado.."));
            listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();

//        table.resetRows();
        System.out.println("Resetie los filtros ");
    }

    public void eliminar(Persona per) throws Exception {
        try {
            dao.eliminar(per);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Eliminado.."));
            limpiar();
            listar();
        } catch (Exception e) {
        }

    }

    public void listar() throws Exception {
        try {
            listadoper = dao.listarPer();
        } catch (Exception e) {
            throw e;
        }

    }

    public void listarLector() throws Exception {
        try {
            listadoLector = dao.listarLector();
        } catch (Exception e) {
            throw e;
        }

    }

    public void limpiar() throws Exception {
        try {
            this.persona.setCODPER(0);
            this.persona.setNOMPER("");
            this.persona.setAPEPER("");
            this.persona.setDNIPER("");
            this.persona.setTIPPER("");
            this.persona.setGRADAUL("");
            this.persona.setSECCAUL("");
            this.persona.setSECTAUL("");
            this.persona.setCODUBI("");
            this.persona.setUSUPER("");
            this.persona.setPASSPER("");
        } catch (Exception e) {
            throw e;
        }

    }

    public void reportePersona() throws Exception {
        try {
            Map<String, Object> parameters = new HashMap();
            reporte.exportarPersonaPDF(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updatePass() {
        dao = new PersonaImpl();
        try {

            if (dao.actualizarPass(persona) == true) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Actulización", "Datos Actualizados.."));
                limpiar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Datos Incorrectos.."));
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar metodo udatePas :v");
        }
    }
//Para completar el InputText

    public List<String> completeTextUbigeo(String query) throws SQLException, Exception {
        PersonaImpl daoPer = new PersonaImpl();
        return daoPer.autocompleteUbigeo(query);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListadoper() {
        return listadoper;
    }

    public void setListadoper(List<Persona> listadoper) {
        this.listadoper = listadoper;
    }

    public List<Persona> getListadoper2() {
        return listadoper2;
    }

    public void setListadoper2(List<Persona> listadoper2) {
        this.listadoper2 = listadoper2;
    }

    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }

    public reporteP getReporte() {
        return reporte;
    }

    public void setReporte(reporteP reporte) {
        this.reporte = reporte;
    }

    public List<Persona> getListadoLector() {
        return listadoLector;
    }

    public void setListadoLector(List<Persona> listadoLector) {
        this.listadoLector = listadoLector;
    }

}
