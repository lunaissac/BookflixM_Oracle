package graficos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "año_libro_ChartJS")
@SessionScoped
public class año_libro_ChartJS implements Serializable {

    private LineChartModel lineModel;
    private List<año_libro> lstañolibro;
    dao_año_libro dao = new dao_año_libro();

    public año_libro_ChartJS() {
    }
    
    @PostConstruct
    public void iniciar() {
        try {
//          createLineModel();  
        } catch (Exception e) {
            System.out.println("Error al graficar Linea");
        }
        
    }

    public void createLineModel() throws Exception {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        //Formato de la linea
        dataSet.setFill(false);
        dataSet.setLabel("Cantidad");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        //Arrays a usar
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        lstañolibro = dao.lstAñoLibro();
        
         for (año_libro al : lstañolibro) {
             values.add(al.getCANTIDAD());
             labels.add(String.valueOf(al.getANIO()));
        }
        
        //Guardado de datos
        dataSet.setData(values);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);
         
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Estrenos de Libros");
        options.setTitle(title);
         
        lineModel.setOptions(options);
        lineModel.setData(data);
    }    

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }
    
    public List<año_libro> getLstañolibro() {
        return lstañolibro;
    }

    public void setLstañolibro(List<año_libro> lstañolibro) {
        this.lstañolibro = lstañolibro;
    }

    public dao_año_libro getDao() {
        return dao;
    }

    public void setDao(dao_año_libro dao) {
        this.dao = dao;
    }

}
