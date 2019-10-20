package controller;

import Reportes.reporteP;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import dao.LibroImpl;
import dao.PDetalleImpl;
import dao.PersonaImpl;
import dao.PrestamoImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Libro;
import model.Persona;
import model.Prestamo;
import model.Usuario;

@Named(value = "prestamoC")
@SessionScoped

public class PrestamoC implements Serializable {

    private Prestamo prestamo = new Prestamo();
    private PrestamoImpl daoPrestamo = new PrestamoImpl();
    private List<Prestamo> listadopres;

    Usuario usuario;

    @ManagedProperty("#{libroC}")
    LibroC libroC;

//Datos a Usar
    Libro milibro;
    Persona miLector;
    
    private PersonaImpl daoPersona = new PersonaImpl();
    private LibroImpl daoLibro = new LibroImpl();
    private LibroC librocontrol;
    private List<Libro> listadoLibro;

    private PDetalleImpl prestamodetalle;
    private reporteP reportes;
    
    
    //CODIGO A BUSCAR
    private int CodigoBusca; 
    private int CodigoLector;
    
    //VARIABLE PARA FECHA DEL SISTEMA
    private String fechSistema;

    public PrestamoC() {
        listadopres = new ArrayList();
        listadoLibro = new ArrayList();
        milibro = new Libro();
        librocontrol = new LibroC();
        prestamodetalle = new PDetalleImpl();
        reportes = new reporteP();
        usuario = new Usuario();
    }

    @PostConstruct
    public void iniciarPrestamo() {
        try {
//            listarLibro();
            listarPrestamo();
        } catch (Exception e) {
        }
    }

    private static int codigo_Bibliotecario;

    public int codigoBibliotecario(int codigoV) {
        codigo_Bibliotecario = codigoV;
        return codigoV;
    }

    public void registrarPrestamo() throws Exception {
        try {

            prestamo.setCODBIBL(String.valueOf(codigo_Bibliotecario));

            if (Emprestar() == true) {
                daoPrestamo.registrar(this.prestamo);

                //Aqui reguistramos el detalle del prestamo
                daoPrestamo.UltimoRegistro(prestamo);
                prestamodetalle.registrarprestamo(prestamo);

//                listarLibro();
                listarPrestamo();
                clean();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Guardado.."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Cantidad", "Insufuciente.."));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarPrestamo() throws Exception {
        try {
            prestamo.setCODBIBL(String.valueOf(codigo_Bibliotecario));
            prestamo.setCODLECT(daoPrestamo.obtenerCodigoLector(prestamo.getCODLECT()));
            daoPrestamo.modificar(this.prestamo);
            limpiarPrestamo();
            listarPrestamo();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Modificado.."));
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarPrestamo(Prestamo pres) throws Exception {
        try {
            daoPrestamo.eliminar(pres);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Eliminado.."));
            listarPrestamo();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarPrestamo() throws Exception {
        try {
            listadopres = daoPrestamo.listarPre();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiarPrestamo() throws Exception {
        try {
            this.prestamo.setCODBIBL("");
            this.prestamo.setCODLECT("");
            this.prestamo.setCODLIB(0);
            this.prestamo.setCANTFAL(0);

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean Emprestar() throws Exception {

        int id = prestamo.getCODLIB();
        System.out.println("El codigo de Libro es :" + prestamo.getCODLIB());

        int cantidadsolicitada = prestamo.getCANTFAL();
        int cantidadActual = obtenerCantidad();
        int resultado;

        System.out.println("La cantidad solicitada es :" + cantidadsolicitada);
        System.out.println("La cantidad del libro es :" + cantidadActual);

        if (cantidadsolicitada <= cantidadActual) {

            resultado = cantidadActual - cantidadsolicitada;
            daoLibro.ActualizarStock(id, resultado);
            System.out.println("Queda " + resultado + " Libros");
            return true;

        } else {
            System.out.println("Cantidad Insuficiente");
        }
        return false;
    }

    public int obtenerCantidad() throws Exception {
        listadoLibro = daoLibro.listarLib();
        milibro.setCODLIB(String.valueOf(prestamo.getCODLIB()));
        return daoLibro.obtenerCantidad(listadoLibro, milibro);
    }

    public void listarLibro() throws Exception {
        try {
            listadoLibro = daoLibro.listarLib();
//            libroC.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void clean() {
        this.prestamo.setCODLECT(null);
        this.prestamo.setCODBIBL(null);
        this.prestamo.setCANTFAL(0);
        this.prestamo.setCODLIB(0);
        this.prestamo.setCODLECT(null);
        
        this.milibro.setESTLIB(null);
        this.milibro.setCANTLIB(null);
        this.milibro.setCODCAT(null);
        this.milibro.setDIRUBILIB(null);
        
        this.miLector.setDNIPER(null);
        this.miLector.setGRADAUL(null);
        this.miLector.setSECCAUL(null);
        this.miLector.setSECTAUL(null);
    }
    
    public void buscarLibro() {
        CodigoBusca = prestamo.getCODLIB();
        milibro = new Libro();
        try {
            milibro = daoLibro.buscarLibroC(CodigoBusca);
        } catch (Exception e) {
        }
    }
    
    public void buscarLector() {
        CodigoLector = Integer.parseInt(prestamo.getCODLECT());
        miLector = new Persona();
        try {
            miLector = daoPersona.buscarLectorC(CodigoLector);
        } catch (Exception e) {
        }
    }

    

    public void reportePrestamo() throws Exception {
        try {
            Map<String, Object> parameters = new HashMap();
            reportes.exportarPrestamoPDF(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    // ------------ AUTOCOMPLETAR BIBLIOTECARIO -----------------------------
//    public List<String> completeTextBibliotecario(String query) throws SQLException, Exception {
//        PrestamoImpl daoPres = new PrestamoImpl();
//        return daoPres.autocompleteBibliotecario(query);
//    }

    // ------------ AUTOCOMPLETAR LECTOR -----------------------------
    public List<String> completeTextLector(String query) throws SQLException, Exception {
        PrestamoImpl daoPres = new PrestamoImpl();
        return daoPres.autocompleteLector(query);
    }
    
    
     public String getFechSistema() throws ParseException {
        Date fechaActual = new Date();
        DateFormat fechaF = new SimpleDateFormat("dd/MM/yyyy");
        this.fechSistema = fechaF.format(fechaActual);
        return fechSistema;
    }

    public static int getCodigo_Bibliotecario() {
        return codigo_Bibliotecario;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public PrestamoImpl getDaoPrestamo() {
        return daoPrestamo;
    }

    public void setDaoPrestamo(PrestamoImpl daoPrestamo) {
        this.daoPrestamo = daoPrestamo;
    }

    public List<Prestamo> getListadopres() {
        return listadopres;
    }

    public void setListadopres(List<Prestamo> listadopres) {
        this.listadopres = listadopres;
    }

    public Libro getMilibro() {
        return milibro;
    }

    public void setMilibro(Libro milibro) {
        this.milibro = milibro;
    }

    public List<Libro> getListadoLibro() {
        return listadoLibro;
    }

    public void setListadoLibro(List<Libro> listadoLibro) {
        this.listadoLibro = listadoLibro;
    }

    public LibroImpl getDaoLibro() {
        return daoLibro;
    }

    public void setDaoLibro(LibroImpl daoLibro) {
        this.daoLibro = daoLibro;
    }

    public LibroC getLibrocontrol() {
        return librocontrol;
    }

    public void setLibrocontrol(LibroC librocontrol) {
        this.librocontrol = librocontrol;
    }

    public LibroC getLibroC() {
        return libroC;
    }

    public void setLibroC(LibroC libroC) {
        this.libroC = libroC;
    }

    public int getCodigoBusca() {
        return CodigoBusca;
    }

    public void setCodigoBusca(int CodigoBusca) {
        this.CodigoBusca = CodigoBusca;
    }

    public int getCodigoLector() {
        return CodigoLector;
    }

    public void setCodigoLector(int CodigoLector) {
        this.CodigoLector = CodigoLector;
    }

    public PersonaImpl getDaoPersona() {
        return daoPersona;
    }

    public void setDaoPersona(PersonaImpl daoPersona) {
        this.daoPersona = daoPersona;
    }

    public Persona getMiLector() {
        return miLector;
    }

    public void setMiLector(Persona miLector) {
        this.miLector = miLector;
    }
    
    
}
