package controller;

import Reportes.reporteP;
import dao.LibroImpl;
import dao.PDetalleImpl;
import dao.PrestamoImpl;
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
import model.PDetalle;
import model.Prestamo;

@Named(value = "devolucionC")
@SessionScoped
public class DevolucionC implements Serializable {

    private PDetalle devolucion;
    private PDetalleImpl dao;
    private List<PDetalle> listadoDevolucion;

    private List<Prestamo> listadoCabecera;
    private PrestamoImpl daoPrestamo;
    Prestamo midevolucion;

    private LibroImpl daoLibroUpdate;
    private reporteP reporte;

    public DevolucionC() {
        dao = new PDetalleImpl();
        devolucion = new PDetalle();
        listadoDevolucion = new ArrayList();

        listadoCabecera = new ArrayList();
        daoPrestamo = new PrestamoImpl();
        midevolucion = new Prestamo();

        daoLibroUpdate = new LibroImpl();
        reporte = new reporteP();
    }

    @PostConstruct
    public void inicia() {
        try {
            listar();
            limpiar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        try {
            if (devolver() == true) {
                dao.registrar(devolucion);
                ActualizarLibro();
                System.out.println("Entrar a registro");
                limpiar();
                listar();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completado.."));
            } else {
                listar();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Cantidad", "Excedida.."));

            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void modificar() throws Exception {
//        try {
//            dao = new PDetalleImpl();
////            devolucion.setFECDEV(fecha.format(fechaFormulario));
//            dao.modificar(devolucion);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado.."));
//            limpiar();
//            listar();
//        } catch (Exception e) {
//            throw e;
//        }

    }

    public void eliminar(PDetalle devolucion) throws Exception {
        try {
            dao = new PDetalleImpl();
            dao.eliminar(devolucion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", "Completo..."));
            limpiar();
            listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listadoDevolucion = dao.listarDevolucion();
            System.out.println("Voy a imprimir la lsita de devolcuion " + listadoDevolucion.toString());

        } catch (Exception e) {
            throw e;
        }

    }

    public void limpiar() throws Exception {
        try {
            this.devolucion.setCANTDET("1");            
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean devolver() throws Exception {

        int id = devolucion.getCODPRESD();
        System.out.println("El codigo de Prestamo es :" + devolucion.getCODPRESD());

        int cantidadsolicitada = Integer.parseInt(devolucion.getCANTDET());
        int cantidadActual = obtenerCantidadCabecera();
        int resultado;

        System.out.println("La cantidad a devolver es :" + cantidadsolicitada);
        System.out.println("La cantidad emprestada es :" + cantidadActual);

        if (cantidadsolicitada <= cantidadActual) {

            resultado = cantidadActual - cantidadsolicitada;

            dao.ActualizarCabecera(id, resultado);
            System.out.println("Queda " + resultado + " Libros");
            return true;

        } else {
            System.out.println("Cantidad Exedida");
        }
        return false;
    }

    public int obtenerCantidadCabecera() throws Exception {
        System.out.println("Voy a imprimir tu Cabecera");
        listadoCabecera = daoPrestamo.listarPre();
        midevolucion.setCODPRESD(devolucion.getCODPRESD());
        return dao.obtenerCantidadCabecera(listadoCabecera, midevolucion);
    }

    public void ActualizarLibro() throws Exception {

        int id = obtenerCodigoUpdate();
        System.out.println("Codigo de libro a actualizar :" + id);

        int cantidadsuma = Integer.parseInt(devolucion.getCANTDET());
        System.out.println("Cantidad a Sumar :" + cantidadsuma);

        int cantidadLibro = obtenerCantidadUpdate();
        System.out.println("Cantidad de Libro Actual :" + cantidadLibro);

        int resultado = cantidadLibro + cantidadsuma;
        System.out.println("Suma Final :" + resultado);

        daoLibroUpdate.ActualizarStock(id, resultado);
    }

    public int obtenerCantidadUpdate() throws Exception {
        int codigo = devolucion.getCODPRESD();
        return daoLibroUpdate.obtenerCantidadUpdate(codigo);
    }

    public int obtenerCodigoUpdate() throws Exception {
        int codigo = devolucion.getCODPRESD();
        System.out.println("CODIGO A BUSCAR E IR A LIBRO :" + codigo);
        return daoLibroUpdate.obtenerCodigoUpdate(codigo);
    }

    public void reporteDevolucion() throws Exception {
        try {
            Map<String, Object> parameters = new HashMap();
            reporte.exportarDevolucionPDF(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public PDetalle getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(PDetalle devolucion) {
        this.devolucion = devolucion;
    }

    public List<PDetalle> getListadoDevolucion() {
        return listadoDevolucion;
    }

    public void setListadoDevolucion(List<PDetalle> listadoDevolucion) {
        this.listadoDevolucion = listadoDevolucion;
    }

    public PDetalleImpl getDao() {
        return dao;
    }

    public void setDao(PDetalleImpl dao) {
        this.dao = dao;
    }

    public PrestamoImpl getDaoPrestamo() {
        return daoPrestamo;
    }

    public void setDaoPrestamo(PrestamoImpl daoPrestamo) {
        this.daoPrestamo = daoPrestamo;
    }

    public List<Prestamo> getListadoCabecera() {
        return listadoCabecera;
    }

    public void setListadoCabecera(List<Prestamo> listadoCabecera) {
        this.listadoCabecera = listadoCabecera;
    }

    public LibroImpl getDaoLibroUpdate() {
        return daoLibroUpdate;
    }

    public void setDaoLibroUpdate(LibroImpl daoLibroUpdate) {
        this.daoLibroUpdate = daoLibroUpdate;
    }

    public reporteP getReporte() {
        return reporte;
    }

    public void setReporte(reporteP reporte) {
        this.reporte = reporte;
    }

}
