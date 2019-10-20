package graficos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "pre_Dev_ChartJS")
@SessionScoped
public class Pre_Dev_ChartJS implements Serializable {

    private BarChartModel barModel2;

    private List<mes_Pre_Dev> lstMesPreDev;
    dao_mes_Pre_Dev dao = new dao_mes_Pre_Dev();

    @PostConstruct
    public void init() {
        try {
//            createBarModel2();
        } catch (Exception e) {
        }

    }

//    public String cantLibros() {
//        String Cantidad = dao.cantLibros();
//        return Cantidad;
//    }

    public List<mes_Pre_Dev> getLstMesPreDev() {
        return lstMesPreDev;
    }

    public void setLstMesPreDev(List<mes_Pre_Dev> lstMesPreDev) {
        this.lstMesPreDev = lstMesPreDev;
    }

    public void createBarModel2() throws Exception {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        //Mi lista a Recorrer
        lstMesPreDev = dao.lstPrestamoDevolucion();

        //Alistamos la barra 1
        BarChartDataSet barDataSet1 = new BarChartDataSet();
        barDataSet1.setLabel("Prestamos");
        barDataSet1.setBackgroundColor("rgba(255, 99, 132,0.2)");
        barDataSet1.setBorderColor("rgb(255, 99, 132)");
        barDataSet1.setBorderWidth(1);

        //Alistamos la barra 2
        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel("Devoluciones");
        barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
        barDataSet2.setBorderColor("rgb(255, 159, 64)");
        barDataSet2.setBorderWidth(1);

        //Alistamos los nuevos Arrays
        List<Number> Prestamo = new ArrayList<>(); //Barra 1 - Prestamo
        List<Number> Devolucion = new ArrayList<>(); //Barra2 - Devolucion
        List<String> labels = new ArrayList<>(); //Los Meses

        for (mes_Pre_Dev lpv : lstMesPreDev) {
            switch (lpv.getMES()) {
                case "Enero":
                    if (lpv.getTIPO().equals("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equals("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Febrero":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Marzo":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Abril":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Mayo":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Junio":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Julio":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Agosto":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Septiembre":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Octubre":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Noviembre":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                case "Diciembre":
                    if (lpv.getTIPO().equalsIgnoreCase("P")) {
                        Prestamo.add(lpv.getCANTIDAD());
                        labels.add(lpv.getMES());
                    } else if (lpv.getTIPO().equalsIgnoreCase("D")) {
                        Devolucion.add(lpv.getCANTIDAD());
                    }
                    break;
                default:
                    System.out.println("No hay nada que mostrar");
            }
        }
        //Agregamos los datos
        barDataSet1.setData(Prestamo);
        barDataSet2.setData(Devolucion);

        //Organización de los Datos
        data.addChartDataSet(barDataSet1);
        data.addChartDataSet(barDataSet2);

        data.setLabels(labels);
        barModel2.setData(data);

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
        title.setText("Prestamos y Devoluciones en cada Mes");
        options.setTitle(title);

        barModel2.setOptions(options);
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
