package graficos;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "prestamos_devolucionC")
@SessionScoped

public class prestamo_devolucionC implements Serializable {

    private PieChartModel pieModel;
    private List<prestamo_devolucion> lstPresDev;

    @PostConstruct
    public void init() {
        try {
            lstCantPresDev();
        } catch (Exception ex) {
//            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lstCantPresDev() throws Exception {
        try {
            dao_prestamo_devolucion dao = new dao_prestamo_devolucion();
            lstPresDev = dao.lstPrestamoDevolucion();
            graficarCantPresDev(lstPresDev);
        } catch (SQLException e) {
            throw e;
        }
    }

    //Graficar un grafico estadistico en forma de pie con la información)
    public void graficarCantPresDev(List<prestamo_devolucion> lista) {
        pieModel = new PieChartModel();
        for (prestamo_devolucion predev : lstPresDev) {
            pieModel.set(predev.getTIPO(), predev.getCANTIDAD());
        }
        pieModel.setTitle("Porcentaje de Prestamos y Devoluciones");
        pieModel.setLegendPosition("ne");
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(200);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<prestamo_devolucion> getLstPresDev() {
        return lstPresDev;
    }

    public void setLstPresDev(List<prestamo_devolucion> lstPresDev) {
        this.lstPresDev = lstPresDev;
    }

}
