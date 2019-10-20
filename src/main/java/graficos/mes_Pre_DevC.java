package graficos;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "mes_Pre_DevC")
@SessionScoped

public class mes_Pre_DevC {

    private BarChartModel barModel;

    private List<mes_Pre_Dev> lstMesPreDev;
    dao_mes_Pre_Dev dao = new dao_mes_Pre_Dev();

    @PostConstruct
    public void init() {
        try {
            createBarModel();
        } catch (Exception e) {
        }

    }

    private BarChartModel initBarModel() throws Exception {
        BarChartModel model = new BarChartModel();

        lstMesPreDev = dao.lstPrestamoDevolucion();

        ChartSeries prestamos = new ChartSeries();
        prestamos.setLabel("Prestamos");

        for (mes_Pre_Dev lpv : lstMesPreDev) {
            if (lpv.getTIPO().equalsIgnoreCase("P")) {
                prestamos.set(lpv.getMES(), lpv.getCANTIDAD());
            }
        }

        ChartSeries devoluciones = new ChartSeries();
        devoluciones.setLabel("Devoluciones");
        for (mes_Pre_Dev lpv : lstMesPreDev) {
            if (lpv.getTIPO().equalsIgnoreCase("D")) {
                devoluciones.set(lpv.getMES(), lpv.getCANTIDAD());
            }
        }

        model.addSeries(prestamos);
        model.addSeries(devoluciones);

        return model;
    }

    private void createBarModel() throws Exception {
        barModel = initBarModel();

        barModel.setTitle("Prestamos - Devoluciones");
        barModel.setLegendPosition("ne");
        barModel.setAnimate(true);

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Meses");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(160);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<mes_Pre_Dev> getLstMesPreDev() {
        return lstMesPreDev;
    }

    public void setLstMesPreDev(List<mes_Pre_Dev> lstMesPreDev) {
        this.lstMesPreDev = lstMesPreDev;
    }

}
