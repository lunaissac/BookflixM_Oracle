package graficos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

@ManagedBean(name = "año_libroC")
@SessionScoped

public class año_libroC implements Serializable {

    private LineChartModel lineModel2;

    private BarChartModel barModel;

    private List<año_libro> lstañolibro;

    private List<Alumnos> listAlumnos;
    private String CLibros;
    private String CLectores;
    private String CLibrosPendientes;
    private String CDevoluciones;

    dao_año_libro dao = new dao_año_libro();

    @PostConstruct
    public void init() {
        try {
//            createLineModels();
//            createBarModel();
        } catch (Exception e) {
            System.out.println("Error al graficar");
        }
    }

    //METODO CANTIDAD DE LIBROS
    public String CLibros() {
        setCLibros(dao.Libros());
        return CLibros;
    }

    //METODO CANTIDAD DE LECTORES
    public String CLectores() {
        setCLectores(dao.cantLectores());
        return CLectores;
    }

    //METODO CANTIDAD DE LIBROS PEDIENTES
    public String CLibrosPendientes() {
        setCLibrosPendientes(dao.cantDevPendientes());
        return CLibrosPendientes;
    }

    //METODO CANTIDAD DE DEVOLUCIONES
    public String CDevoluciones() {
        setCDevoluciones(dao.cantDevoluciones());
        return CDevoluciones;
    }

    //METODO GRAFICO
    private void createLineModels() throws Exception {
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Estrenos de Libros");
        lineModel2.setAnimate(true);
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Años"));
        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(120);
    }

    private LineChartModel initCategoryModel() throws Exception {
        LineChartModel model = new LineChartModel();
        lstañolibro = dao.lstAñoLibro();
        System.out.println("DATOS-->" + lstañolibro.toString());

        ChartSeries libro = new ChartSeries();
        libro.setLabel("Libro");
        for (año_libro al : lstañolibro) {
            libro.set(al.getANIO(), al.getCANTIDAD());
        }
        model.addSeries(libro);

        return model;
    }

    //METODO PARA GRAFICAR CANTIDAD DE ALUMNOS POR GRADOS
    public void createBarModel() throws Exception {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        listAlumnos = dao.ListAlumnos();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Cantidad");

        List<Number> values = new ArrayList<>();
        for (Alumnos alum : listAlumnos) {
            values.add(alum.getCANTIDAD());
        }
        values.add(50);
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("1° Grado");
        labels.add("2° Grado");
        labels.add("3° Grado");
        labels.add("4° Grado");
        labels.add("5° Grado");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("");
//        title.setText("Cantidad de Alumnos"); 
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }

    public List<año_libro> getLstañolibro() {
        return lstañolibro;
    }

    public void setLstañolibro(List<año_libro> lstañolibro) {
        this.lstañolibro = lstañolibro;
    }

    public String getCLibros() {
        return CLibros;
    }

    public void setCLibros(String CLibros) {
        this.CLibros = CLibros;
    }

    public String getCLectores() {
        return CLectores;
    }

    public void setCLectores(String CLectores) {
        this.CLectores = CLectores;
    }

    public String getCLibrosPendientes() {
        return CLibrosPendientes;
    }

    public void setCLibrosPendientes(String CLibrosPendientes) {
        this.CLibrosPendientes = CLibrosPendientes;
    }

    public String getCDevoluciones() {
        return CDevoluciones;
    }

    public void setCDevoluciones(String CDevoluciones) {
        this.CDevoluciones = CDevoluciones;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<Alumnos> getListAlumnos() {
        return listAlumnos;
    }

    public void setListAlumnos(List<Alumnos> listAlumnos) {
        this.listAlumnos = listAlumnos;
    }

}
