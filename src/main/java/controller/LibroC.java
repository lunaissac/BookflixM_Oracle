package controller;

import Reportes.reporteP;
import dao.LibroImpl;
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
import model.Libro;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "libroC")
@SessionScoped
public class LibroC implements Serializable {

    Libro libro;
    private LibroImpl dao;
    private List<Libro> listadoLibro;
    private List<Libro> ListadoFiltro;
    private Libro selectLib;

    //Objetos para reporte
    private String Tipo;

    private PieChartModel pieModel;

    public LibroC() {
        try {
            dao = new LibroImpl();
            libro = new Libro();
            listadoLibro = dao.listarLib();
            selectLib = new Libro();
        } catch (Exception e) {
        }
    }

    @PostConstruct
    public void iniciar() {
        try {
//            listar();
//            listaLibroGrafico();
//            obtenerCantidad();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(libro);
            libro.clear();
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completo..."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            libro.setCODAUT(String.valueOf(libro.getCODAUT2()));
            libro.setCODCAT(String.valueOf(libro.getCODCAT2()));
            libro.setCODEDIT(String.valueOf(libro.getCODEDIT2()));
//            libro.setCODAUT(dao.obtenerCodigoAutor(libro.getCODAUT()));
//            libro.setCODCAT(dao.obtenerCodigoCategoria(libro.getCODCAT()));
//            libro.setCODEDIT(dao.obtenerCodigoEditorial(libro.getCODEDIT()));
//            selectLib.setCODAUT(dao.obtenerCodigoAutor(selectLib.getCODAUT()));
//            selectLib.setCODCAT(dao.obtenerCodigoCategoria(selectLib.getCODCAT()));
//            selectLib.setCODEDIT(dao.obtenerCodigoEditorial(selectLib.getCODEDIT()));
            dao.modificar(libro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Completo..."));
//            libro.clear();
            resetearFitrosTabla("form:dtTable");
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
        System.out.println("Resetie los filtros ");
    }

    public void eliminar(Libro libro) throws Exception {
        try {
            dao.eliminar(libro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Completo..."));
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void clear() {
        libro.clear();
    }

    public void listar() throws Exception {
        try {
            System.out.println("AQUI");
            listadoLibro = dao.listarLib();
        } catch (Exception e) {
            throw e;
        }
    }

    public void reporteLibro() throws Exception {
        reporteP reportes = new reporteP();
        try {
            Map<String, Object> parameters = new HashMap();
            parameters.put("TIPO", getTipo());
            reportes.exportarLibroPDF(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listaLibroGrafico() throws Exception {
        try {
            dao = new LibroImpl();
            listadoLibro = dao.listarLib();
            graficarLibro(listadoLibro);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void graficarLibro(List<Libro> lista) {
        System.out.println("Metodo Graficar Libros");

        pieModel = new PieChartModel();
        for (Libro lib : listadoLibro) {
            pieModel.set(lib.getTITLIB(), Integer.valueOf(lib.getCANTLIB()));
            System.out.println(lib.toString());
        }
        pieModel.setTitle("Porcentaje de Libros");
        pieModel.setLegendPosition("ne");
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(200);
    }

//    public int obtenerCantidad() throws Exception{
//        return dao.obtenerCantidad(listadoLibro, libro);
//    }
// ------------ AUTOCOMPLETAR AUTOR -----------------------------
    public List<String> completeTextAutor(String query) throws SQLException, Exception {
        LibroImpl daoPer = new LibroImpl();
        return daoPer.autocompleteAutor(query);
    }

// ----------------AUTOCOMPLETAR CATEGORIA ------------------------
    public List<String> completeTextCategoria(String query) throws SQLException, Exception {
        LibroImpl daoPer = new LibroImpl();
        return daoPer.autocompleteCategoria(query);
    }

// ---------------AUTOCOMPLETAR EDITORIAL -------------------------
    public List<String> completeTextEditorial(String query) throws SQLException, Exception {
        LibroImpl daoPer = new LibroImpl();
        return daoPer.autocompleteEditorial(query);
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public List<Libro> getListadoLib() {
        return listadoLibro;
    }

    public void setListadoLibro(List<Libro> listadoLibro) {
        this.listadoLibro = listadoLibro;
    }

    public LibroImpl getDao() {
        return dao;
    }

    public void setDao(LibroImpl dao) {
        this.dao = dao;
    }

    public List<Libro> getListadoFiltro() {
        return ListadoFiltro;
    }

    public void setListadoFiltro(List<Libro> ListadoFiltro) {
        this.ListadoFiltro = ListadoFiltro;
    }

    public Libro getSelectLib() {
        return selectLib;
    }

    public void setSelectLib(Libro selectLib) {
        this.selectLib = selectLib;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

}
