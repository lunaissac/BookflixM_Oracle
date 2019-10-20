package Reportes;

import dao.Conexion;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class reporteP extends Conexion {

    public void exportarEditPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Editorial.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Editorial.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarEditPDF: " + e.getMessage());
        }
    }

    public void exportarLibroPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Libro.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Libro.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarLibroPDF: " + e.getMessage());
        }
    }

    public void exportarPersonaPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Lector.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Lectores.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarPersonaPDF: " + e.getMessage());
        }
    }

    public void exportarAutorPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Autor.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Autores.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarAutorPDF: " + e.getMessage());
        }
    }

    public void exportarCategoriaPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Categorias.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Categorias.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarPersonaPDF: " + e.getMessage());
        }
    }

    public void exportarPrestamoPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Prestamos.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Prestamos.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarPersonaPDF: " + e.getMessage());
        }
    }

    public void exportarDevolucionPDF(Map parameters) throws JRException, IOException, Exception {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Devoluciones.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Devoluciones.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("ERROR en exportarPersonaPDF: " + e.getMessage());
        }
    }

}
