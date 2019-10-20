package graficos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

@Named(value = "prestamo_devolucion_ChartJS")
@SessionScoped
public class prestamo_devolucion_ChartJS implements Serializable {

    private PieChartModel pieModel;
    private List<prestamo_devolucion> lstPresDev;

    @PostConstruct
    public void init() {
        try {
//            createPieModel();
        } catch (Exception ex) {
        }
    }

    public prestamo_devolucion_ChartJS() {
    }

    public void createPieModel() throws Exception {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        dao_prestamo_devolucion dao = new dao_prestamo_devolucion();
        lstPresDev = dao.lstPrestamoDevolucion();

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (prestamo_devolucion predev : lstPresDev) {
            labels.add(predev.getTIPO());
            values.add(predev.getCANTIDAD());
        }

        dataSet.setData(values);
        data.setLabels(labels);
        
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);


        pieModel.setData(data);
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
